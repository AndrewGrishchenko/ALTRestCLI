package restclient.restclient;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

import restclient.restclient.models.BranchBinaryPackagesMessage;
import restclient.restclient.models.NameArchPair;
import restclient.restclient.models.PackageMessage;
import restclient.restclient.models.ResponseMessage;

@Service
public class RestService {
    @Value("https://rdb.altlinux.org/api")
    private String baseURI;

    private void writeJson (String fileName, ResponseMessage responseMessage) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            String json = mapper.writeValueAsString(responseMessage);

            FileWriter fileWriter = new FileWriter(fileName);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(json);
            printWriter.close();
        } catch (JsonProcessingException e) {
            System.out.println("json error");
        } catch (IOException e) {
            System.out.println("io error");
        }
    }

    private void presenceDiff (BranchBinaryPackagesMessage firstPackagesMessage, BranchBinaryPackagesMessage secondPackagesMessage, String fileName) {
        List<PackageMessage> firstPackagesList = firstPackagesMessage.getPackages();
        int firstPackagesMessageSize = firstPackagesList.size();
        System.out.println("retrieved FIRST " + String.valueOf(firstPackagesMessageSize) + " packages");

        BranchDiffLibrary.MessageStruct.ByReference packages1Ref = new BranchDiffLibrary.MessageStruct.ByReference();
        BranchDiffLibrary.MessageStruct[] packages1 = (BranchDiffLibrary.MessageStruct[]) packages1Ref.toArray(firstPackagesMessageSize);

        for (int i = 0; i < firstPackagesMessageSize; i++) {
            packages1[i].name = firstPackagesList.get(i).getName();
            packages1[i].arch = firstPackagesList.get(i).getArch();
            packages1[i].version = firstPackagesList.get(i).getVersion();
        }


        List<PackageMessage> secondPackagesList = secondPackagesMessage.getPackages();
        int secondPackagesMessageSize = secondPackagesList.size();
        System.out.println("retrieved SECOND " + String.valueOf(secondPackagesMessageSize) + " packages");

        BranchDiffLibrary.MessageStruct.ByReference packages2Ref = new BranchDiffLibrary.MessageStruct.ByReference();
        BranchDiffLibrary.MessageStruct[] packages2 = (BranchDiffLibrary.MessageStruct[]) packages2Ref.toArray(secondPackagesMessageSize);

        for (int i = 0; i < secondPackagesMessageSize; i++) {
            packages2[i].name = secondPackagesList.get(i).getName();
            packages2[i].arch = secondPackagesList.get(i).getArch();
            packages2[i].version = secondPackagesList.get(i).getVersion();
        }


        PointerByReference valsRefPtr = new PointerByReference();
        IntByReference numValsRef = new IntByReference();
        BranchDiffLibrary.INSTANCE.presenceDiff(packages1Ref, firstPackagesMessageSize, packages2Ref, secondPackagesMessageSize, valsRefPtr, numValsRef);
        int numVals = numValsRef.getValue();
        Pointer pVals = valsRefPtr.getValue();
        BranchDiffLibrary.MessageStruct valsRef = new BranchDiffLibrary.MessageStruct(pVals);
        valsRef.read();
        BranchDiffLibrary.MessageStruct[] vals = (BranchDiffLibrary.MessageStruct[]) valsRef.toArray(numVals);
        
        System.out.println("retrieved " + String.valueOf(numVals));
        ResponseMessage responseMessage = new ResponseMessage();
        for (BranchDiffLibrary.MessageStruct val : vals) {
            if (val.name == null) break;
            responseMessage.addPackage(firstPackagesMessage.getPackageByName(new NameArchPair(val.name, val.arch)));
        }

        BranchDiffLibrary.INSTANCE.cleanupMessageStruct(pVals);
        
        writeJson(fileName, responseMessage);
    }

    public String branchDiff (String branch1, String branch2, String fileName) {
        BranchBinaryPackagesMessage firstPackagesMessage = restClient().get().uri(baseURI + "/export/branch_binary_packages/" + branch1)
        .retrieve().body(BranchBinaryPackagesMessage.class);

        BranchBinaryPackagesMessage secondPackagesMessage = restClient().get().uri(baseURI + "/export/branch_binary_packages/" + branch2)
        .retrieve().body(BranchBinaryPackagesMessage.class);
        
        presenceDiff(firstPackagesMessage, secondPackagesMessage, fileName);

        return "JSON writed to " + fileName;
    }

    public String branchDiff (String branch1, String branch2, String arch, String fileName) {
        BranchBinaryPackagesMessage firstPackagesMessage = restClient().get().uri(baseURI + "/export/branch_binary_packages/" + branch1 + "?arch=" + arch)
        .retrieve().body(BranchBinaryPackagesMessage.class);

        BranchBinaryPackagesMessage secondPackagesMessage = restClient().get().uri(baseURI + "/export/branch_binary_packages/" + branch2 + "?arch=" + arch)
        .retrieve().body(BranchBinaryPackagesMessage.class);
        
        presenceDiff(firstPackagesMessage, secondPackagesMessage, fileName);

        return "JSON writed to " + fileName;
    }

    private void versionDiff (BranchBinaryPackagesMessage firstPackagesMessage, BranchBinaryPackagesMessage secondPackagesMessage, String fileName) {
        List<PackageMessage> firstPackagesList = firstPackagesMessage.getPackages();
        int firstPackagesMessageSize = firstPackagesList.size();
        
        BranchDiffLibrary.MessageStruct.ByReference packages1Ref = new BranchDiffLibrary.MessageStruct.ByReference();
        BranchDiffLibrary.MessageStruct[] packages1 = (BranchDiffLibrary.MessageStruct[]) packages1Ref.toArray(firstPackagesMessageSize);

        for (int i = 0; i < firstPackagesMessageSize; i++) {
            packages1[i].name = firstPackagesList.get(i).getName();
            packages1[i].arch = firstPackagesList.get(i).getArch();
            packages1[i].version = firstPackagesList.get(i).getVersion();
        }


        List<PackageMessage> secondPackagesList = secondPackagesMessage.getPackages();
        int secondPackagesMessageSize = secondPackagesList.size();
        
        BranchDiffLibrary.MessageStruct.ByReference packages2Ref = new BranchDiffLibrary.MessageStruct.ByReference();
        BranchDiffLibrary.MessageStruct[] packages2 = (BranchDiffLibrary.MessageStruct[]) packages2Ref.toArray(secondPackagesMessageSize);

        for (int i = 0; i < secondPackagesMessageSize; i++) {
            packages2[i].name = secondPackagesList.get(i).getName();
            packages2[i].arch = secondPackagesList.get(i).getArch();
            packages2[i].version = secondPackagesList.get(i).getVersion();
        }
        
        PointerByReference valsRefPtr = new PointerByReference();
        IntByReference numValsRef = new IntByReference();
        BranchDiffLibrary.INSTANCE.versionDiff(packages1Ref, firstPackagesMessageSize, packages2Ref, secondPackagesMessageSize, valsRefPtr, numValsRef);
        int numVals = numValsRef.getValue();
        Pointer pVals = valsRefPtr.getValue();
        BranchDiffLibrary.MessageStruct valsRef = new BranchDiffLibrary.MessageStruct(pVals);
        valsRef.read();
        BranchDiffLibrary.MessageStruct[] vals = (BranchDiffLibrary.MessageStruct[]) valsRef.toArray(numVals);

        ResponseMessage responseMessage = new ResponseMessage();
        for (BranchDiffLibrary.MessageStruct val : vals) {
            if (val.name == null) break;
            responseMessage.addPackage(firstPackagesMessage.getPackageByName(new NameArchPair(val.name, val.arch)));
        }

        writeJson(fileName, responseMessage);
    }

    public String branchDiffVersion (String branch1, String branch2, String fileName) {
        BranchBinaryPackagesMessage firstPackagesMessage = restClient().get().uri(baseURI + "/export/branch_binary_packages/" + branch1)
        .retrieve().body(BranchBinaryPackagesMessage.class);

        BranchBinaryPackagesMessage secondPackagesMessage = restClient().get().uri(baseURI + "/export/branch_binary_packages/" + branch2)
        .retrieve().body(BranchBinaryPackagesMessage.class);

        versionDiff(firstPackagesMessage, secondPackagesMessage, fileName);

        return "JSON writed to " + fileName;
    }

    @Bean
    RestClient restClient () {
        return RestClient.create(baseURI);
    }
}
