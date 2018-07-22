import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class RandCommand extends Command {
    private CommandMap map;
    RandCommand(CommandMap commandMap) {
        super("rand");
        this.map = commandMap;
        this.setPermission("rand.use");
        this.setPermissionMessage("you have no permission to use /rand");
        this.setDescription("A plugin developed to make a random argument,this command can only use in CommandBlock");
        this.setUsage("/rand [command],use "+RandExecutor.read_able_pattern+" at where you wang to set a random number");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if(commandSender instanceof BlockCommandSender){
        	if(strings.length > 0) {
                if(strings[0].startsWith("/")) strings[0] = strings[0].substring(1);

                String name = strings[0];
                RandResult result = RandExecutor.execute(Arrays.copyOfRange(strings,1,strings.length));

                return map.getCommand(name).execute(commandSender,name,result.asArray());
        	} else {
        		commandSender.sendMessage("Usage: " + getUsage());
        		return false;
        	}
        } else {
            for (String name : Group.getAllGroupsName()) {
                commandSender.sendMessage(name+",");
            }
        	//commandSender.sendMessage("the command /rand can only use in CommandBlock");
        	//commandSender.sendMessage("you can use other commands,and just replace the arguments with " + RandExecutor.read_able_pattern + " to get a random plugin");
            return false;
        }
    }
}
