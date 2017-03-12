package resources;

public class ResourceServerController implements ResourceServerControllerMBean {
    private IResourceServer iResourceServer;

    public ResourceServerController(IResourceServer iResourceServer) {
        this.iResourceServer = iResourceServer;
    }

    @Override
    public String getName() {
        return iResourceServer.getName();
    }

    @Override
    public int getAge() {
        return iResourceServer.getAge();
    }

    @Override
    public void setName(String name) {
        iResourceServer.setName(name);
    }

    @Override
    public void setAge(int age) {
        iResourceServer.setAge(age);
    }
}
