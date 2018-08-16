import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

/**
 * Created by mtumilowicz on 2018-08-16.
 */
@Value
@Builder
@AllArgsConstructor
class Customer {
    int id;
    String firstName;
    Address address;
}
