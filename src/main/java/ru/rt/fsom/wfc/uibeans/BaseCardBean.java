package ru.rt.fsom.wfc.uibeans;

import java.util.Map;
import java.util.logging.Level;
import org.primefaces.PrimeFaces;
import org.primefaces.event.TabChangeEvent;
import ru.rt.fsom.wfc.data.dict.OpenMode;
import ru.rt.fsom.wfc.data.dict.SysParams;
import ru.rt.fsom.wfc.utils.MsgUtils;

public abstract class BaseCardBean extends BaseFormBean{   
    
    protected String openMode;
    protected boolean isItemChanged = false;
    protected int tabActiveIndex = 0;  
    
    @Override
    protected void doBeforeOpenForm(Map<String, String> params) {	
	if (!params.containsKey(SysParams.ITEM_ID)){
	    MsgUtils.errorMessage("Param [ITEM_ID] not found!");	    
	    LOGGER.log(Level.INFO, "Param ITEM_ID not found!");
	    return;
	}
	if (!params.containsKey(SysParams.OPEN_MODE)){ 
	    openMode = OpenMode.VIEW_MODE;
	} else {                
	    openMode = params.get(SysParams.OPEN_MODE);
	}
	String itemId = params.get(SysParams.ITEM_ID);
        doPrepareOpen(itemId);
    }

    protected abstract void doPrepareOpen(String itemId);
    
    protected void onCancelItemSave(){	
    }
    
    protected void onSaveItemAndClose(){
	
    }
    
    /* Закрытие формы карточки объекта */
    protected void closeForm(Object exitParam) {      
        PrimeFaces.current().dialog().closeDynamic(exitParam);
    }   
    
    public void onTabChange(TabChangeEvent event){
	
    }
        
    public void onItemChange(){
	isItemChanged = true;
    }
    
    public boolean isReadOnly(){
	return OpenMode.VIEW_MODE.equals(openMode);
    }

    /* gets & sets */ 
    public int getTabActiveIndex() {
	return tabActiveIndex;
    }
    public void setTabActiveIndex(int tabActiveIndex) {
	this.tabActiveIndex = tabActiveIndex;
    }
        
}
