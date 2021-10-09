package com.EVA.L;

import org.apache.commons.cli.*;

public class InputArgument {
    Options options = new Options();
    CommandLineParser parser = new DefaultParser();
    HelpFormatter helpFormatter = new HelpFormatter();
    CommandLine cmd = null;
    public InputArgument(String[] args){
        Option opt1 = new Option("h","help",false,"Print help");
        opt1.setRequired(false);
        options.addOption(opt1);

        Option opt2 = new Option("c","configFile",true,"Name server config properties file");
        opt2.setRequired(false);
        options.addOption(opt2);

        try {
            cmd = parser.parse(options,args);
            if (cmd.hasOption('h')){
                helpFormatter.printHelp("searchEngineToy",options);
            }else if (cmd.hasOption('c')){
                System.out.println("下面是设置输出");
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName()+":"+e.getMessage());
            //System.exit(0);
        }


    }
}
