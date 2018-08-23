import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.stream.JsonReader
import spock.lang.Specification 
/**
 * Created by mtumilowicz on 2018-08-16.
 */
class GsonTest extends Specification {

    def "full packed customer to json"() {
        given:
        def address = Address.builder()
                .street("street")
                .city("city")
                .build()

        and:
        def customer = Customer.builder()
                .id(1)
                .firstName("firstName")
                .address(address)
                .build()

        when:
        def customerJson = new Gson().toJson(customer)

        then:
        customerJson == '{"id":1,"firstName":"firstName","address":{"street":"street","city":"city"}}'
    }

    def "full packed customer from json"() {
        given:
        def address = Address.builder()
                .street("street")
                .city("city")
                .build()

        and:
        def expectedCustomer = Customer.builder()
                .id(1)
                .firstName("firstName")
                .address(address)
                .build()

        when:
        JsonReader reader = new JsonReader(new FileReader("src/test/resources/customerFull.json"))
        def customer = new Gson().fromJson(reader, Customer.class)

        then:
        customer == expectedCustomer
    }

    def "empty customer to json (with serializing nulls)"() {
        given:
        def address = Address.builder()
                .build()

        and:
        def customer = Customer.builder()
                .address(address)
                .build()

        when:
        Gson gson = new GsonBuilder().serializeNulls().create()
        def customerJson = gson.toJson(customer)

        then:
        customerJson == '{"id":0,"firstName":null,"address":{"street":null,"city":null}}'
    }

    def "empty customer to json (without serializing nulls)"() {
        given:
        def address = Address.builder()
                .build()

        and:
        def customer = Customer.builder()
                .address(address)
                .build()

        when:
        def customerJson = new Gson().toJson(customer)

        then:
        customerJson == '{"id":0,"address":{}}'
    }

    def "empty customer from json"() {
        given:
        def address = Address.builder()
                .build()

        and:
        def expectedCustomer = Customer.builder()
                .address(address)
                .build()

        when:
        JsonReader reader = new JsonReader(new FileReader("src/test/resources/customerEmpty.json"))
        def customer = new Gson().fromJson(reader, Customer.class)

        then:
        customer == expectedCustomer
    }
    
    def "pretty json"() {
        given:
        def address = Address.builder()
                .street("street")
                .city("city")
                .build()

        and:
        def customer = Customer.builder()
                .id(1)
                .firstName("firstName")
                .address(address)
                .build()
        
        and:
        Gson gson = new GsonBuilder().setPrettyPrinting().create()
        
        expect:
        gson.toJson(customer) == '{\n' +
                '  "id": 1,\n' +
                '  "firstName": "firstName",\n' +
                '  "address": {\n' +
                '    "street": "street",\n' +
                '    "city": "city"\n' +
                '  }\n' +
                '}'
    }
}