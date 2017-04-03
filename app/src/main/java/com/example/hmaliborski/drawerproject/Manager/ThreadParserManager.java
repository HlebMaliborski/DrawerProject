package com.example.hmaliborski.drawerproject.Manager;

import com.example.hmaliborski.drawerproject.Enums.ImageEnum;
import com.example.hmaliborski.drawerproject.ThreadParser.InternetThreadParser;
import com.example.hmaliborski.drawerproject.ThreadParser.ThreadParser;

public class ThreadParserManager {
    public static ThreadParser getImageParser(ImageEnum imageEnum)
    {
        ThreadParser threadParser = null;
        switch (imageEnum)
        {
            case THREAD_INTERNET:
                threadParser = new InternetThreadParser();
                break;
        }
        return threadParser;
    }
}
