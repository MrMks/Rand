import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.SimpleCommandMap;

//import org.bukkit.craftbukkit.v1_11_R1.CraftServer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.RemoteServerCommandEvent;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Rand extends JavaPlugin implements Listener {

    private CommandMap commandMap;
    private Command randCommand;

    @Override
    public void onLoad() {
        super.onLoad();
        getLogger().info("Rand loaded");
        
        //this.commandMap = ((CraftServer)getServer()).getCommandMap();
        try {
            String pack = getServer().getClass().getPackage().getName();
            //getLogger().info(pack);
            this.commandMap = ((SimpleCommandMap)Class.forName(pack+".CraftServer").getMethod("getCommandMap").invoke(getServer()));
        } catch(Exception e) {
        	e.printStackTrace();
        	getLogger().info("Notice: /rand command disabled");
        	return;
        }

        randCommand = new RandCommand(this.commandMap);
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this,this);
        if(this.commandMap != null) commandMap.register("rand", randCommand);
    }

    @Override
    public void onDisable() {
        if(commandMap != null) commandMap.getCommand("rand").unregister(commandMap);
    }

    @EventHandler
    public void serverCommand(ServerCommandEvent event){
        event.setCommand(RandExecutor.execute(event.getCommand()).asString());
    }

    @EventHandler
    public void playerCommand(PlayerCommandPreprocessEvent event){
        event.setMessage(RandExecutor.execute(event.getMessage()).asString());
    }

    @EventHandler
    public void remoteServerCommand(RemoteServerCommandEvent event){
        event.setCommand(RandExecutor.execute(event.getCommand()).asString());
    }

}
