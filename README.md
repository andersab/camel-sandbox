camel-sandbox
=============

Camel research sandbox

##### I recommend running maven site prior to moving much further

### Building and Deploying
After checking out the code, you only need to do a

    mvn clean package

This will produce a zip file in target that can be uncompressed

    unzip target/camel-sandbox-...  <location>

### Starting
After unzipping, you should see a directory called _camel-sandbox_

    +camel-sandbox
      | bin - directory contains executable script
      | lib - where all library dependencies live
      | config - configuration properties
      | logs - logs should be written to here

