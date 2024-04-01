package restclient.restclient;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Structure.FieldOrder;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

public interface BranchDiffLibrary extends Library {
    BranchDiffLibrary INSTANCE = (BranchDiffLibrary)
            Native.load("libbranchdiff", BranchDiffLibrary.class);

    @FieldOrder({"name", "arch", "version"})
    public static class MessageStruct extends Structure {
        public static class ByReference extends MessageStruct implements Structure.ByReference {}

        public String name;
        public String arch;
        public String version;

        public MessageStruct() {}
        public MessageStruct(Pointer p) {
            super(p);
        }
    }
    public void presenceDiff (MessageStruct.ByReference packages1, int packages1Num, MessageStruct.ByReference packages2, int packages2Num, PointerByReference valsRef, IntByReference numValsRef);
    public void cleanupMessageStruct (Pointer p);

    public void versionDiff (MessageStruct.ByReference packages1, int packages1Num, MessageStruct.ByReference packages2, int packages2Num, PointerByReference valsRef, IntByReference numValsRef);
}