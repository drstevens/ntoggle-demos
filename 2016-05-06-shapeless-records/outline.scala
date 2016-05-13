import shapeless.HList
// put in package so this compiles before hand
object outline extends App {

// Two types which represent similar things. How do we map from A to B?
case class ABDC(
a: Option[Double],
b: Option[Int],
d: Option[String],
c: Option[String])

case class BAD(
b: Option[String],
a: Option[String],
d: Option[String])

val abdc = ABDC(
Option(5.55d),
Option(23),
Option("d"),
Option("c"))

// How do we map a to b
BAD(
abdc.a.map(_.toString),
abdc.b.map(_.toString),
abdc.c.map(_.toString))

// Oops anyone spot the bug?

// Shapeless Generic/LabelledGeneric

{
import shapeless.Generic
// Generic maps A to/from HList
val genABDC = Generic[ABDC]

val repABDC = genABDC.to(abdc)
val abdc2 = genABDC.from(repABDC)

// Unfortunately type for repABDC is somewhat obfuscated
// :t repABDC

// This is same
import shapeless.HNil
val hlistABDC = Option(5.55d) :: Option(23) :: Option("d") :: Option("c") :: HNil
hlistABDC == repABDC

genABDC.from(genABDC.to(abdc)) == abdc

println(repABDC)
println(genABDC.from(repABDC) == abdc)

// Can we create a Generic[B]?
val genB = Generic[BAD]
// genB.from(repABDC)
// above won't compile!

// Wrong types
// Wrong number of parameters
// Wrong order

// How to map types?
import shapeless.Poly1
object mapValues extends Poly1 {
  implicit def optInt = at[Option[Int]](_.map(_.toString))
  implicit def optDouble = at[Option[Double]](_.map(_.toString))
  implicit def optString = at[Option[String]](identity[Option[String]])
}

// :t repABDC
val repABDC2 = repABDC.map(mapValues)
// :t repA2

val hlistABDC2 = Option("5.55") :: Option("23") :: Option("d") :: Option("c") :: HNil
hlistABDC == repABDC

genB.from(repABDC2.take(3))
// oops, same issue as earlier

// How do we align values now?
}

{
// First let's talk about extensible records
import shapeless.HNil
import shapeless.syntax.singleton._
val recordABDC = {
  'a ->> Option(5.55d) ::
  'b ->> Option(23) ::
  'd ->> Option("d") ::
  'c ->> Option("c") :: HNil
}
// :t recordABDC

// We can add and remove things
import shapeless.record._ // needed to treat HList as record
'z ->> 10 :: recordABDC
recordABDC + ('z ->> 10) - 'z
recordABDC - 'c


// OK cool, how do we get from Generic to here?

import shapeless.LabelledGeneric
// LabelledGeneric maps A to/from Record
val lgenABDC = LabelledGeneric[ABDC]
lgenABDC.from(lgenABDC.to(abdc))

val lrepABDC = lgenABDC.to(abdc)
// Q: what is lrepABDC here?
// :t lrepABDC

lrepABDC == recordABDC

//Now we need to map
import shapeless.labelled.{FieldType, field}
import shapeless.Poly1
object mapValues extends Poly1 {
  implicit def optInt[K] = at[FieldType[K, Option[Int]]](b =>
    field[K](b.map(_.toString)))

  implicit def optDouble[K] = at[FieldType[K, Option[Double]]](b =>
    field[K](b.map(_.toString)))

  implicit def optString[K] =
    at[FieldType[K, Option[String]]]((b: Option[String]) => field[K](b))
}

val mapped = (lrepABDC - 'c).map(mapValues)
// :t mapped

// Need to rememer that FieldType.toString doesn't show field labels
println(mapped)

// Let's try to create a B now
// it still doesn't compile because of ordering
val lgenBAD = LabelledGeneric[BAD]
// lgenBAD.from(mapped)

// Most of the way there. How do we fix order, or ignore it
import shapeless.ops.hlist.Align
case class BDA(
  b: Option[String],
  d: Option[String],
  a: Option[String])
val lgenBDA = LabelledGeneric[BDA]

val bda = BDA(
  abdc.b.map(_.toString),
  abdc.d.map(_.toString),
  abdc.a.map(_.toString))

val alignBAD = Align[lgenBDA.Repr, lgenBAD.Repr]
val bad2: BAD = lgenBAD.from(alignBAD(lgenBDA.to(bda)))
println(bad2)

// How do we create an align instance without specifying the type of the mapped extensible record?

import shapeless.HList
// http://stackoverflow.com/questions/29242873/shapeless-turn-a-case-class-into-another-with-fields-in-different-order
class ConvertRecordTo[T] {
  def apply[S <: HList, TR <: HList](s: S)(implicit
    genT: LabelledGeneric.Aux[T, TR],
    align: Align[S, TR]) = genT.from(align(s))
}
object ConvertRecordTo {
  def apply[T]: ConvertRecordTo[T] = new ConvertRecordTo[T]
}
// here, the "from" type is inferred, we only need to specify the "to" type
ConvertRecordTo[BAD](lgenBDA.to(bda))

ConvertRecordTo[BAD](mapped)
println(ConvertRecordTo[BAD](mapped))
}
}