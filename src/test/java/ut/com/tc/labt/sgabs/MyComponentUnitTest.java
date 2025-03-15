package ut.com.tc.labt.sgabs;

import org.junit.Test;
import com.tc.labt.sgabs.api.MyPluginComponent;
import com.tc.labt.sgabs.impl.MyPluginComponentImpl;

import static org.junit.Assert.assertEquals;

public class MyComponentUnitTest
{
    @Test
    public void testMyName()
    {
        MyPluginComponent component = new MyPluginComponentImpl(null);
        assertEquals("names do not match!", "myComponent",component.getName());
    }
}