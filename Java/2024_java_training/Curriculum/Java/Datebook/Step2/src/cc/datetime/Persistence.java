/*
Copyright 2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.datetime;

import java.util.ArrayList;
import java.util.List;

/**
Ironically-named class that supplies a data set for our stream code
to process; this can be replaced with real persistence code based on
files or databases.

@author Will Provost
*/
public class Persistence
{
    /**
    Returns a hard-coded list of string representations, as might be found
    in a text file.
    */
    public static List<String> loadDatebook ()
    {
        List<String> result = new ArrayList<> ();

        result.add ("2015-02-23T09:00:00 Discuss pre-meeting");
        result.add ("2015-02-23T09:30:00 Pre-meeting");
        result.add ("2015-02-23T09:50:00 Pre-meeting wrap-up");
        result.add ("2015-02-23T10:00:00 Meeting");
        result.add ("2015-02-23T10:15:00 Debriefing");
        result.add ("2015-02-23T16:00:00 Follow-up conference call");

        result.add ("2015-02-24T09:00:00 Work");
        result.add ("2015-02-24T10:30:00 Break");
        result.add ("2015-02-24T10:45:00 Work");
        result.add ("2015-02-24T12:00:00 Lunch");
        result.add ("2015-02-24T13:00:00 Work");
        result.add ("2015-02-24T14:30:00 Break");
        result.add ("2015-02-24T14:45:00 Work");
        result.add ("2015-02-24T17:00:00 Off work");
        result.add ("2015-02-24T19:30:00 Theatre tickets");

        result.add ("2015-02-26T11:00:00 Call with Karl");

        result.add ("2015-02-28T10:00:00 Gym");
        result.add ("2015-02-28T12:00:00 Museum");
        result.add ("2015-02-28T13:30:00 Baseball game");

        result.add ("2015-03-01T14:00:00 Band practice");

        return result;
    }
}
