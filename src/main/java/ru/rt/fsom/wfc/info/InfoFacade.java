package ru.rt.fsom.wfc.info;

import javax.ejb.Stateless;
import ru.rt.fsom.wfc.ejb.BaseFacade;

/**
 *
 * @author Maksim.Filatov
 */
@Stateless
public class InfoFacade extends BaseFacade{    
    
    public int getDbStatus(){
	InfoRestClient client = new InfoRestClient(conf.gerRestApiURL());
	int result = client.getDbStatus();
	client.close();
	return result;
    }
    
}
