import org.apache.commons.cli.*;

import java.util.Arrays;

public class Main {

    private String[] args;
    private Options options;
    private Integer i_key;
    private String s_key;
    private String[] sArr;
    private Integer[] iArr;
    private boolean isInterger;

    public static void main(String args[])
    {

        Main mylist = new Main();

        int ret;

        mylist.parse(args);

        if(mylist.isInterger())
        {
            ret = mylist.binSearch(mylist.getiArr(), mylist.getI_key());
        }
        else
        {
            ret = mylist.binSearch(mylist.getsArr(),mylist.getS_key());
        }


        System.out.println(ret);
    }

    @SuppressWarnings("unchecked")
    public int binSearch(Comparable[] aList, Comparable key)
    {

        int mid;
        int beg = 0;
        int last = aList.length-1;

        if (aList.length == 0)
            return -1;
        for (int cpt = 0; cpt < aList.length/2; cpt++) {
            mid = beg + (last - beg)/2;

            //System.out.println(aList[mid].compareTo(key));

            if(aList[mid].compareTo(key) == 0)
            {
                return 1;
            }
            else if (aList[mid].compareTo(key) > 0)
            {
                last = mid-1;
            }
            else
            {
                beg = mid+1;
            }
        }
        return 0;
    }

    private void setIntegerType(String inputType)
    {
        if(inputType.equals("i"))
            isInterger = true;
        else if (inputType.equals("s"))
            isInterger = false;
        else
        {
            System.out.println("illegal type");
            System.exit(0);
        }

    }

    private void setKey(String inputKey)
    {
        if(isInterger)
            i_key = new Integer(inputKey);
        else
            s_key = new String(inputKey);
    }


    public void parse(String args[])
    {
        options = new Options();
        this.args = args;
        options.addOption("h", "help", false, "show help.");
        options.addOption("t", "type", true, "Set type:“i” for integer and “s” for string.");
        options.addOption("k", "key", true, "Set search key.");
        Option option = new Option("l", "list", true, "Set your list of integers or strings.");
        option.setArgs(Option.UNLIMITED_VALUES);
        options.addOption(option);

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;
        try
        {
            cmd = parser.parse(options, args);

            if (cmd.hasOption("type"))
                setIntegerType(cmd.getOptionValue("type"));
            if (cmd.hasOption("key"))
                setKey(cmd.getOptionValue("key"));
            if (cmd.hasOption("list"))
            {
                if(isInterger)
                    iArr = Arrays.stream(cmd.getOptionValues("list")).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);
                else
                    sArr = cmd.getOptionValues("list");
            }

        }
        catch (ParseException e)
        {
            System.out.println("Failed to parse comand line properties");
        }
    }

    boolean isInterger()
    {
        return isInterger;
    }

    String getS_key()
    {
        return s_key;
    }

    Integer getI_key()
    {
        return i_key;
    }

    String[] getsArr()
    {
        return sArr;
    }

    Integer[] getiArr()
    {
        return iArr;
    }

}
