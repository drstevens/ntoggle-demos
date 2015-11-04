# Grep for start of JSON object at start of line to grab only bid requests
```
michael@abrode:~/dev/demos/2015-10-30-jq$ grep ^{ ngrep-output.txt | head -n 2
{"app":{"bundle":"com.myyearbook.m","cat":["IAB24","social_networking"],"id":"5ac0c886fa3f47eeadc4c19afe46fa2c","name":"MeetMe Android","publisher":{"id":"c3584d1c40cb43619e043c065caee33a","name":"MeetMe, Inc."},"ver":"10.3.3.2"},"at":2,"badv":["badoo","chatmeup","fling","happn","howaboutwe","imvu","jaumo","justhookup","lavaplace","lovoo","meetmoi","meowchat","moco","momo","on.com","pof","sayhi","skout","tagged","tango","thegamebyhotornot","tinder","twoo","wechat","whisper","zoosk","landrover","landrover.com","landrovertristate.com"],"bcat":["IAB25","IAB25-2","IAB25-3","IAB25-4","IAB25-5","IAB26","IAB26-1","IAB26-2","IAB26-3","IAB26-4","IAB3-7"],"device":{"carrier":"311-480","connectiontype":2,"devicetype":1,"dnt":1,"dpidmd5":"be3633513162ef8a65f31db5ad832ba2","dpidsha1":"3b4f6bbbc0bc3865a5ee26f6c032224e5d08348d","ext":{"idfa":"f848b65b-6963-4689-8077-a002d31a30a5"},"geo":{"city":"Zanesville","country":"USA","lat":39.913094,"lon":-82.000938,"metro":"596","region":"OH","zip":"43701"},"ip":"75.185.9.30","js":1,"language":"en","make":"samsung","model":"SM-G900V","os":"Android","osv":"5.0","ua":"Mozilla/5.0 (Linux; Android 5.0; SM-G900V Build/LRX21T; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/46.0.2490.76 Mobile Safari/537.36"},"id":"313e43cc-dbac-49b1-8697-2e5fadeb538c","imp":[{"banner":{"api":[3,5],"battr":[1,3,8,9,10,14,6],"btype":[4],"ext":{"nativebrowserclick":1},"h":250,"pos":1,"w":300},"bidfloor":1.390,"displaymanager":"mopub","displaymanagerver":"3.7.0","ext":{"secure":0},"id":"1","instl":0,"tagid":"4085d4f0a067481a9ad13462ff444808"}],"user":{"keywords":"AGE:28,DEV_MODEL:SM-G900V,RELATIONSHIP_STATUS:COMPLICATED,DEV_BRAND:Verizon,BUILD_TYPE:release,ETHNICITY:WHITE_CAUCASIAN,EDUCATION:ASSOCIATES_DEGREE,GENDER:m,PARENTAL_STATE:NON_PARENT,RELIGION:CHRISTIAN,DEV_TYPE:kltevzw,DEV_MFR:samsung,INTERESTED_IN:WOMEN,APP_VERSION:259"}}
{"app":{"bundle":"se.feomedia.quizkampen.fr.lite","cat":["IAB9","IAB9-30","games"],"id":"0d955f13ef374142b63c38d152bbdac9","name":"FR_Duelquiz_Android","publisher":{"id":"3d608beaae34442aa3ca8eb54be6bf05","name":"FEO Media AB"},"ver":"2.2.0"},"at":2,"badv":["bestgameklub.com","bigfishgames.com","bipmod.com","bipmod.fr","borntoberichandroid.smashatomsoftwarellc.com","cellfish.com","cervomedia.com","doubledowncasino.com","dragoncity.socialpoint.com","dragonlandsios.socialquantum.com","enel.it","etermax.com","gagnerdescadeaux.com","galaxyempire.tap4fun.com","gameofwar-fireage.addmired","inc.com","gametwist.com","grepolisappios.innogames.com","http://enjoythegames.mobi/","http://flexidea.ro","http://onlinegamez.mobi/","http://sdc.tre.it/","http://www.playweez.com/?t2c=a0a0646d35ab18bea98234185fde5d2d4ada&ptn=sfr_liquidm","http://yourgames.mobi/","lovoo.com","lovooios.lovoogmbh.com","mbttd.com","msplash-it.bestgameklub.com","netintheworld.com","nitw","powerfulmedia.fr","quiz-fever.de","quizpeoplectc.reactivpub.com","sonymobile.com","squeezmeios.studiocadet.com","ss_centro_flurrycom_flurry_appcircle_cpc_ct_320x50_mar2014.cthealthexchange001.com","tel:","tel:0899025093","tel:0899025232","tel:0899025722","tel:0899025723","tel:0899025728","tel:0899028539","wap.spielplatzplus.de","www.kia.com","www.lovoo.com","yodarling.gorillagaminggmbh.com"],"bcat":["IAB25","IAB26","IAB7-39","IAB8-18","IAB8-5","IAB9-7","IAB9-9"],"device":{"carrier":"208-10","connectiontype":2,"devicetype":1,"dnt":0,"dpidmd5":"d50e03c102dd7af5d4ca592e8c55ef5c","dpidsha1":"2661278da99b36b1ff8e0dbfca177b4fd6830928","ext":{"idfa":"7388ad1f-d414-447b-82d5-0f5812172738"},"geo":{"city":"Gap","country":"FRA","region":"B8","zip":"05000"},"ip":"93.1.138.219","js":1,"language":"fr","make":"MOBIWIRE","model":"STARTRAIL5","os":"Android","osv":"4.4.2","ua":"Mozilla/5.0 (Linux; Android 4.4.2; STARTRAIL5 Build/KOT49H) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/30.0.0.0 Mobile Safari/537.36"},"id":"e2666006-2d92-4970-9d60-621d79414e2b","imp":[{"banner":{"api":[3,5],"battr":[1,2,3,5,6,7,8,9,10,13,14],"btype":[4],"ext":{"nativebrowserclick":1},"h":50,"pos":1,"w":320},"bidfloor":1.390,"displaymanager":"mopub","displaymanagerver":"3.13.0","ext":{"secure":0},"id":"1","instl":0,"tagid":"d6abb51c246546b5ac2e44a1223902a1"}]}
```
# Basic jq usage pretty prints JSON
```
michael@abrode:~/dev/demos/2015-10-30-jq$ grep ^{ ngrep-output.txt | jq . | head -n 10
{
  "user": {
    "keywords": "AGE:28,DEV_MODEL:SM-G900V,RELATIONSHIP_STATUS:COMPLICATED,DEV_BRAND:Verizon,BUILD_TYPE:release,ETHNICITY:WHITE_CAUCASIAN,EDUCATION:ASSOCIATES_DEGREE,GENDER:m,PARENTAL_STATE:NON_PARENT,RELIGION:CHRISTIAN,DEV_TYPE:kltevzw,DEV_MFR:samsung,INTERESTED_IN:WOMEN,APP_VERSION:259"
  },
  "imp": [
    {
      "tagid": "4085d4f0a067481a9ad13462ff444808",
      "instl": 0,
      "id": "1",
      "ext": {
```
# Return all country objects (we will return to parse error later)
```
michael@abrode:~/dev/demos/2015-10-30-jq$ grep ^{ ngrep-output.txt | jq .device.geo | head -n 20
parse error: Invalid numeric literal at line 8, column 6
{
  "zip": "43701",
  "region": "OH",
  "metro": "596",
  "lon": -82.000938,
  "lat": 39.913094,
  "country": "USA",
  "city": "Zanesville"
}
{
  "zip": "05000",
  "region": "B8",
  "country": "FRA",
  "city": "Gap"
}
{
  "zip": "32606",
  "region": "FL",
  "metro": "592",
  "lon": -82.38961,

```
# Return only geo country
```
michael@abrode:~/dev/demos/2015-10-30-jq$ grep ^{ ngrep-output.txt | jq ".device.geo | {countryName: .country}" | head -n 10
parse error: Invalid numeric literal at line 8, column 6
{
  "countryName": "USA"
}
{
  "countryName": "FRA"
}
{
  "countryName": "USA"
}
{
```
# Return only geo country and city
```
michael@abrode:~/dev/demos/2015-10-30-jq$ grep ^{ ngrep-output.txt | jq ".device.geo | {countryName: .country, city: .city}" | head -n 20
parse error: Invalid numeric literal at line 8, column 6
{
  "city": "Zanesville",
  "countryName": "USA"
}
{
  "city": "Gap",
  "countryName": "FRA"
}
{
  "city": "Gainesville",
  "countryName": "USA"
}
{
  "city": null,
  "countryName": "USA"
}
{
  "city": "Springfield",
  "countryName": "USA"
}
```
# Return banner per imp array index
```
michael@abrode:~/dev/demos/2015-10-30-jq$ grep ^{ ngrep-output.txt | jq ".imp[].banner" | head -n 20
parse error: Invalid numeric literal at line 8, column 6
{
  "w": 300,
  "pos": 1,
  "h": 250,
  "ext": {
    "nativebrowserclick": 1
  },
  "btype": [
    4
  ],
  "battr": [
    1,
    3,
    8,
    9,
    10,
    14,
    6
  ],
  "api": [
```
# Access non-existent imp array index
```
michael@abrode:~/dev/demos/2015-10-30-jq$ grep ^{ ngrep-output.txt | jq ".imp[1].banner"
null
null
null
null
null
null
```
# Return entire mraid array
```
michael@abrode:~/dev/demos/2015-10-30-jq$ grep ^{ ngrep-output.txt | jq ".imp[].banner | {mraid: .api}" | head -n 20
parse error: Invalid numeric literal at line 8, column 6
{
  "mraid": [
    3,
    5
  ]
}
{
  "mraid": [
    3,
    5
  ]
}
{
  "mraid": [
    3
  ]
}
{
  "mraid": [
    3,

```
# Return length of the mraid array
```
michael@abrode:~/dev/demos/2015-10-30-jq$ grep ^{ ngrep-output.txt | jq ".imp[0].banner.api | length"
2
2
1
2
2
1

```
# Create predicate for banner height = 250px
```
michael@abrode:~/dev/demos/2015-10-30-jq$ grep ^{ ngrep-output.txt | jq ".imp[0].banner.h == 250" 
true
false
false
false
false
false
```

# Handling JSON parse errors
To handle the parse errors, jq 1.5+ supports `--seq` flag to parse JSON as 
per [https://tools.ietf.org/html/rfc7464#section-2](https://tools.ietf.org/html/rfc7464#section-2).
Still need to find a tool/write a script that will transform input JSON into 
json-seq format for relaxed parsing.  This will allow ngrep and jq to be used
in a streaming fashion.

# Alternative examples with tcpdump
Johnny showed the following command that works with tcpdump instead of ngrep:
```
sudo tcpdump -s 65535 -nn -A -l -i any dst port 4242 | grep ^{ | jq '.imp[0].banner.ext.native’`
```

Ashley shared an alternative version using tcpdump:
```
tcpdump -t -q -s 0 -A 'tcp dst port 4242 and (tcp[((tcp[12:1] & 0xf0) >> 2):4] = 0x504f5354)’
```
