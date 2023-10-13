package Services;

import Responses.ClearResponse;

/**
 * clear service
 * clears all the databases
 */
public class ClearService {
    /**
     * clear
     * @return clear response
     */
    public ClearResponse clear(){
        return new ClearResponse("");
    }
}
