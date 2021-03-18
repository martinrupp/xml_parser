# xml_parser: A simple XML Parser Framework

This is just adding some convenience interfaces on top of SAX to get a parser that is
basically as fast as SAX but as convenient as full DOM scanners.

As performance benchmark one can use XMLAnalyzer which will print the
Type of each XML Node (e.g. String/Double/Integer).
For the 638 MB file (extract https://download.geofabrik.de/antarctica-latest.osm.bz2)
this takes 7.1 s on my laptop, so about 100MB/s.
