package restclient.restclient.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResponseMessage {
    private HashMap<String, List<PackageMessage>> packages = new HashMap<String, List<PackageMessage>>();

    public void addPackage (PackageMessage packageMessage) {
        if (!packages.containsKey(packageMessage.getArch())) 
            packages.put(packageMessage.getArch(), new ArrayList<PackageMessage>());
        
        packages.get(packageMessage.getArch()).add(packageMessage);
    }

    public HashMap<String, List<PackageMessage>> getPackages () {
        return packages;
    }

    @Override
    public String toString () {
        String s = "";
        for (String key : packages.keySet()) {
            s += "USING ARCH= " + key;
            // for (PackageMessage pkg : packages.get(key)) {
            //     s += pkg.toString();
            // }
            for (int i = 0; i < packages.get(key).size(); i++) {
                if (i == 5) break;
                s += "\n" + packages.get(key).get(i).toString();
            }
        }
        return s;
    }
}
