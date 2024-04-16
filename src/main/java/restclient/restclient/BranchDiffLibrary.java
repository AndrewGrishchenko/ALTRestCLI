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
            Native.load("branchdiff", BranchDiffLibrary.class);

    @FieldOrder({"name", "epoch", "version", "release", "arch"})
    public static class MessageStruct extends Structure {
        public static class ByReference extends MessageStruct implements Structure.ByReference {}

        public String name;
        public int epoch;
        public String version;
        public String release;
        public String arch;

        public MessageStruct() {}
        public MessageStruct(Pointer p) {
            super(p);
        }
    }
    public void presenceDiff (MessageStruct.ByReference packages1, int packages1Num, MessageStruct.ByReference packages2, int packages2Num, PointerByReference valsRef, IntByReference numValsRef);
    public void cleanupMessageStruct (Pointer p);

    public void versionDiff (MessageStruct.ByReference packages1, int packages1Num, MessageStruct.ByReference packages2, int packages2Num, PointerByReference valsRef, IntByReference numValsRef);
}