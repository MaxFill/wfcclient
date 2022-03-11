package ru.rt.fsom.wfc.uibeans.login;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.omnifaces.cdi.ViewScoped;
import org.primefaces.PrimeFaces;
import ru.rt.fsom.wfc.data.users.User;
import ru.rt.fsom.wfc.data.dict.SysParams;
import ru.rt.fsom.wfc.ejb.UserFacade;
import ru.rt.fsom.wfc.uibeans.AppBean;
import ru.rt.fsom.wfc.uibeans.SessionBean;
import ru.rt.fsom.wfc.utils.MsgUtils;

/* Контролер формы логина */
@Named
@ViewScoped
public class LoginBean implements Serializable{    
    private static final long serialVersionUID = 4390983938416752289L;
    private static final Logger LOGGER = Logger.getLogger(LoginBean.class.getName());
    
    private String userName;
    private String password;
    private String targetPage;
    private Integer countErrLogin = 0;
    private User user;
	
    @EJB private UserFacade userFacade;    
    @Inject private AppBean appBean;
    @Inject private SessionBean sessionBean;
    
    @PostConstruct
    protected void init(){   
    };
    
    public String login() throws NoSuchAlgorithmException{
        Set<FacesMessage> errors = new HashSet<>();
        if (StringUtils.isEmpty(userName) ||  StringUtils.isEmpty(password)){
            //LOGGER.log(Level.INFO, "LOGIN: login for userName: {0}", userName + "_" + password);
            MsgUtils.errorMsg("BadUserOrPassword");
            return "";
        }
        
        user = userFacade.checkUserByLogin(userName, password.toCharArray());        

        if(user == null) {
            LOGGER.log(Level.INFO, "LOGIN: user check fail for userName: {0}", userName);
            errors.add(MsgUtils.prepFormatErrorMsg("BadUserOrPassword", new Object[]{}));
        }
        if(!errors.isEmpty()) {
            makeCountErrLogin(errors);
            MsgUtils.showFacesMessages(errors);
            return "";
        }

        //LOGGER.log(Level.INFO, "LOGIN: user {0} check OK", userName);

        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute(SysParams.USER_LOGIN, userName);
	appBean.addActiveUser(httpSession.getId(), user);
	sessionBean.setCurrentUser(user);
	LOGGER.log(Level.INFO, "LOGIN: start session for user={0} total session={1}", new Object[]{userName, appBean.countActiveUser()});
        if (StringUtils.isBlank(targetPage) || targetPage.contains(SysParams.LOGIN_PAGE)){
            targetPage = SysParams.MAIN_PAGE;            
        } 
        StringBuilder sb = new StringBuilder(targetPage);        
        if (targetPage.contains("?")){
            sb.append("&");
        } else {
            sb.append("?");
        }
        sb.append("faces-redirect=true");
        return sb.toString();
    }    
    
    /* Сброс счётчика количества неудачных попыток входа  */
    public void resetLoginLock(){
        countErrLogin = 0;
    }
    
    public boolean isLoginLock(){
        return countErrLogin > 2;
    }

    /* *** privates *** */                  
    
    /* Увеличивает счётчик ошибок входа и генерирует сообщение в случае превышения допустимого числа ошибок  */
    private void makeCountErrLogin(Set<FacesMessage> errors){
        countErrLogin++;
        if (isLoginLock()){
            PrimeFaces.current().executeScript("PF('poll').start();");
            errors.add(MsgUtils.prepFormatErrorMsg("ErrorCountLogin", new Object[]{}));
        } 
    }
	
    /* *** Gets & Sets *** */
    
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }    
    
    public String getTargetPage() {
        return targetPage;
    }
    public void setTargetPage(String targetPage) {
        this.targetPage = targetPage;
    }
        
}