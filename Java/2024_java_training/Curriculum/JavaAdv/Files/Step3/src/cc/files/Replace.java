/*
Copyright 2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.files;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
Utility that replaces all quotes with HTML &amp;quot; escape sequences
while producing an HTML-viewable copy of a source file.

@author Will Provost
*/
public class Replace
{
    /**
    Copy the given file (command-line argument or default filename) --
    first to the console, and then to a new HTML file -- while replacing
    double quote characters &quot; with the HTML escape sequence &amp;quot;.
    */
    public static void main (String[] args)
    {
        Path source = Paths.get
            (args.length > 0 ? args[0] : "src/cc/files/Replace.java");
        Path target = Paths.get
            (args.length > 1 ? args[1] : "output/Replaced.html");

        try
        {
            // First, to the console -- stateless, easier:
            Files.lines (source).forEach
                (line -> System.out.println (line.replace ("\"", "&quot;")));

            // Then, to a file -- stateful, awkward to do inline,
            // so we break out to a conventional for loop:
            Files.createDirectories (target.getParent ());
            try ( PrintWriter out = new PrintWriter
                    (new FileWriter (target.toString ())); )
            {
                out.println ("<html><head>");
                out.println ("<title>Replace.java</title>");
                out.println ("<style>p { font-family: monospace;");
                out.println ("           white-space: pre;");
                out.println ("           margin: 0; }</style>");
                out.println ("</head><body>");

                try ( Stream<String> lines = Files.lines(source); ) {
                    lines.map(line -> "<p>" + line.replace ("\"", "&quot;") + "</p>")
                            .forEach(out::println);
                }

                out.println ("</body></html>");
            }
        }
        catch (Exception ex)
        {
            System.out.println ("Couldn't process " + source);
            ex.printStackTrace ();
        }
    }
}
