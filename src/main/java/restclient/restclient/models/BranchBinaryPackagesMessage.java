package restclient.restclient.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BranchBinaryPackagesMessage {
    Map<String, String> request_args;
    int length;
    List<PackageMessage> packages;

    @JsonIgnore
    HashMap<NameArchPair, PackageMessage> packagesMap = new HashMap<NameArchPair, PackageMessage>();

    public Map<String, String> getRequest_args () {
        return request_args;
    }

    public void setRequest_args (Map<String, String> request_args) {
        this.request_args = request_args;
    }

    public int getLength () {
        return length;
    }

    public void setLength (int length) {
        this.length = length;
    }

    public List<PackageMessage> getPackages () {
        return packages;
    }

    public void setPackages (List<PackageMessage> packages) {
        this.packages = packages;
        for (PackageMessage pkg : packages) {
            packagesMap.put(new NameArchPair(pkg.getName(), pkg.getArch()), pkg);
        }
    }

    public PackageMessage getPackageByName (NameArchPair pair) {
        return packagesMap.get(pair);
    }

    @Override
    public String toString () {
        String s = "BranchBinaryPackages{request_args={\n";
        for (String key : request_args.keySet()) {
            s += "\"" + key + "\": \"" + request_args.get(key) + "\"\n";
        }
        s += "}\nlength=" + String.valueOf(length) + "\npackages={";
        for (int i = 0; i < packages.size(); i++) {
            s += "\n" + packages.get(i);
        }
        return s;
    }
}
