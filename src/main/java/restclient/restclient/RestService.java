package restclient.restclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import restclient.restclient.models.BranchBinaryPackagesMessage;

@Service
public class RestService {
    @Value("https://rdb.altlinux.org/api")
    private String baseURI;
    
    public String helloTo (String username) {
        return BranchDiffLibrary.INSTANCE.helloTo(username);
    }
    
    public String ExportBranchBinaryPackages (String branch) {
        return restClient().get().uri(baseURI + "/export/branch_binary_packages/" + branch)
        .retrieve().body(BranchBinaryPackagesMessage.class).toString();
    }

    public String ExportBranchBinaryPackages (String branch, String arch) {
        return restClient().get().uri(baseURI + "/export/branch_binary_packages/" + branch + "?arch=" + arch)
        .retrieve().body(BranchBinaryPackagesMessage.class).toString();
    }

    @Bean
    RestClient restClient () {
        return RestClient.create(baseURI);
    }
}
