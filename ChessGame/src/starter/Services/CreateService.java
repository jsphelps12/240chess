package Services;

import Requests.CreateRequest;
import Responses.CreateResponse;

/**
 * create service
 * creates a new game
 */
public class CreateService {
    /**
     * create
     * @param request create request
     * @return create response
     */
    public CreateResponse create(CreateRequest request){
        return new CreateResponse("",1);
    }
}
