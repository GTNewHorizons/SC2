package vswe.stevescarts.Modules;

public interface IActivatorModule {

    public boolean isActive(int id);

    public void doActivate(int id);

    public void doDeActivate(int id);

}
