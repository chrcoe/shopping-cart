package helloworld;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;

public class HelloActionBean implements ActionBean {
    private ActionBeanContext context;
    private String name;

    public ActionBeanContext getContext() {
        return context;
    }

    public void setContext(ActionBeanContext context) {
        this.context = context;
    }

    public String getName() {
        if (name == null)
            return "World";
        else
            return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DefaultHandler
    public Resolution helloname() {
        name = getName();
        return new ForwardResolution("/helloworld/index.jsp");
    }

}
