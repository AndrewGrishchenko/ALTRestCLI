package restclient.restclient;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Structure;
import com.sun.jna.Structure.FieldOrder;

public interface BranchDiffLibrary extends Library {
    BranchDiffLibrary INSTANCE = (BranchDiffLibrary)
            Native.load("/home/andrew/Projects/restClient/branchdifflib/c/libbranchdiff.so", BranchDiffLibrary.class);
        
    void diff ();
    String helloTo (String username);

    @FieldOrder({"num1", "num2"})
    public static class ExampleStruct extends Structure {
        public int num1;
        public int num2;
    }

    ExampleStruct modifyStruct (ExampleStruct s);
}