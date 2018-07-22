
public class test {
    public static void main(String[] args){
        Number a = 1;
        System.out.println(a.toString());
        a = 1.09;
        System.out.println(a.toString());

        String[] sources = {
                "#rand[-1,1]", //short standard
                "#rand[-9999999999999,99999999999999]", //out of range
                "#rand[1,7]{1}",
                "#rand[1,7]{0}",
                "#rand[1,7]{1,0}",
                "#rand[1.1,1.9]{1}",
                "#rand[1.9,2.1]{1}",
                "#rand[0.9,1.1]{1,0,group3}",
                "#rand[0.9,1.1]{1,0,group3}",
                "#rand[0.9,1.1]{1,1,group3}",
                "#rand[0.9,0.91]{1,1,group1,0}",
                "#rand[0.9,0.91]{1,1,group1,0}",
                "#rand[0.9,0.91]{1,1,group1,1}",
                "#rand[0,10]{1,0,group2,0}",
                "#rand[0,10]{1,0,group2,0}",
                "#rand[0,10]{1,0,group2,0}",
                "#rand[0,10]{1,0,group2,0}",
                "#rand[0,10]{1,0,group2,0}",
                "#rand[0,10]{1,1,group2,1}",
                "#rand[0,10]{1,1,group2,0}",
                "#rand[0,10]{1,0,group2,1}",
                "#rand[0,10]{1,0,group2,0}",
        };
        for(String source:sources){
            RandResult result = RandExecutor.execute(source);
            System.out.println(result.asString());
        }
        for (String name : Group.getAllGroupsName()) {
            System.out.println(name);
        }
    }
}
