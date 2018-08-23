[![Build Status](https://travis-ci.com/mtumilowicz/gson.svg?token=PwyvjePQ7aiAX51hSYLE&branch=master)](https://travis-ci.com/mtumilowicz/gson)

# gson
Exploring basic features of google's GSON library.

_Reference_: https://github.com/google/gson

# project description
We serialize / deserialize objects from / to json using `GSON`.

# manual
* gson
    * to json
        ```
        def customerJson = new Gson().toJson(customer)
        ```
    * from json
        ```
        JsonReader reader = new JsonReader(new FileReader("src/test/resources/customerFull.json"))
        def customer = new Gson().fromJson(reader, Customer.class)
        ```
    * with serializing nulls
        ```
        Gson gson = new GsonBuilder().serializeNulls().create()
        def customerJson = gson.toJson(customer)        
        ```
    * pretty (well formated) json
        ```
        Gson gson = new GsonBuilder().setPrettyPrinting().create()
        println gson.toJson(customer)  
        ```
