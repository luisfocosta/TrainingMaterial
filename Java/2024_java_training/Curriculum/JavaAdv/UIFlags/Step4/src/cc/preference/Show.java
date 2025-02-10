/*
Copyright 2005 Will Provost.
All rights reserved by Capstone Coursesware, LLC.
*/

package cc.preference;

import java.lang.annotation.*;
import static java.lang.annotation.RetentionPolicy.*;
import static java.lang.annotation.ElementType.*;

/**
This annotation type allows flagging of preferences that should be shown
in a dynamically-generated preferences dialog.

@author Will Provost
*/
@Target (FIELD)
@Retention (RUNTIME)
public @interface Show
{
}
