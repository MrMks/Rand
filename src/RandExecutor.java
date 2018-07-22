import java.util.regex.Matcher;
import java.util.regex.Pattern;

class RandExecutor {
    static String read_able_pattern = "#rand[a,b]{0(double)/1(long),0(different)/1(same),0/1(do)}";
    private static String short_pat = "#rand\\[(-?\\d+\\.?\\d*),(-?\\d+\\.?\\d*)]";
    private static String long_pat = short_pat + "\\{(.*?)}";

    static RandResult execute(String[] args) {
        String[] new_args = new String[args.length];
        Boolean match = false;
        int i = 0;
        for (String arg : args) {
            RandResult result = execute(arg);
            match = result.find();
            new_args[i] = result.asString();
            i++;
        }
        return new RandResult(match, new_args);
    }

    static RandResult execute(String source) {
        StringBuffer target = new StringBuffer(source);
        boolean matched = longExecute(target);
        matched = shortExecute(target) || matched;
        return new RandResult(matched, target.toString());
    }

    static private Pattern pattern_l = Pattern.compile(long_pat);
    static private boolean longExecute(StringBuffer target) {
        Matcher matcher = pattern_l.matcher(target);
        boolean matched = false;
        while (matcher.find()) {
            matched = true;
            String[] args = matcher.group(3).split(",");
            int first, second, fourth;
            double a = Double.valueOf(matcher.group(1));
            double b = Double.valueOf(matcher.group(2));
            switch (args.length) {
                case 1:
                    try {
                        first = Integer.valueOf(args[0]);
                    } catch (Exception ignored) {
                        first = 0;
                    }
                    stringExecute(target,matcher,first);
                    break;
                case 3:
                case 4:
                    try {
                        first = Integer.valueOf(args[0]);
                        second = Integer.valueOf(args[1]);
                        fourth = args.length == 4 ? Integer.valueOf(args[3]) : 0;
                        if ((first == 0 || first == 1) && (second == 0 || second == 1) && (fourth == 0 || fourth == 1)) {
                            boolean t = Group.createGroup(args[2], first, second, a, b, fourth);
                            if (t) {
                                replaceString(target,matcher,Group.getGroup(args[2]).getResult());
                                //target.replace(matcher.start(0), matcher.end(0), Group.getGroup(args[2]).getResult());
                                break;
                            }
                        }
                    } catch (Exception ignored) {
                    }
                default:
                case 0:
                case 2:
                	replaceString(target,matcher.start(3) - 1, matcher.end(3) + 1, "");
                    //target.delete(matcher.start(3) - 1, matcher.end(3) + 1);
                    break;
            }
            matcher = pattern_l.matcher(target);
        }
        return matched;
    }
    
    static private Pattern pattern_s = Pattern.compile(short_pat);
    static private boolean shortExecute(StringBuffer target){
        Matcher matcher = pattern_s.matcher(target);
        boolean matched = false;
        while(matcher.find()) {
            matched=true;
            stringExecute(target,matcher,0);
            matcher = pattern_s.matcher(target);
        }
        return matched;
    }

    static private void stringExecute(StringBuffer target, Matcher matcher, int returnType) {
        NumberResult result;
        result = getResult(matcher.group(1),matcher.group(2),returnType);
        
        replaceString(target,matcher,result.toString());
    }

    private static NumberResult getResult(String a, String b, int returnType){
        double t_a = Double.valueOf(a);
        double t_b = Double.valueOf(b);

        return getResult(Math.min(t_a,t_b),Math.max(t_a,t_b),returnType);
    }

    static NumberResult getResult(double min, double max, int returnType){
        max = setInRange(max);
        min = setInRange(min);

        if(Math.floor(max) - Math.ceil(min) < 0 && returnType == 1) return new NumberResult(getDoubleResult(min, max),0);

        return new NumberResult(returnType == 1 ? getLongResult(min, max) : getDoubleResult(min, max),returnType);
    }

    static double getDoubleResult(double min, double max) {
        return Math.random() * (max - min) + min;
    }

    static long getLongResult(double min, double max) {
        return Math.round(Math.random() * (Math.floor(max) - Math.ceil(min)) + Math.ceil(min));
    }

    static private double setInRange(double number){
        double abs = Math.abs(number);
        return abs >= 30_000_000 ? (number/abs)*30_000_000 : number;
    }

    static private void replaceString(StringBuffer tar, Matcher matcher, String content){
        replaceString(tar,matcher.start(0),matcher.end(0),content);
    }

    static private void replaceString(StringBuffer tar, int start, int end, String content){
        tar.replace(start,end,content);
    }    

}
