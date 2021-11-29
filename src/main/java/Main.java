import com.google.api.gax.paging.Page;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.StorageOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

class Main {

    public static ArrayList<File> upload_files;

    enum State {
        MainMenu,
        FileUpload,
        UploadToGCP,
        SuccessfulProcessing,
        SearchForTerm,
        TopN,
    }

    public static void main(String[] args) throws IOException {

        upload_files = new ArrayList<File>();
        Scanner input = new Scanner(System.in);
        int choice = 0;
        State state = State.MainMenu;

        while (true) {
            switch (state) {
                // -----------------------------------------------------------------------------------------------------
                case MainMenu:

                    System.out.println("\n\n\n\n\nSelect option:");
                    System.out.println(" 1. Choose Files");
                    System.out.println(" 2. Upload and Process Files");

                    choice = input.nextInt();
                    input.nextLine(); // Fixes bug

                    switch (choice) {
                        case 1:
                            state = State.FileUpload;
                            break;
                        case 2:
                            state = State.UploadToGCP;
                            break;
                    }
                    break;


                // -----------------------------------------------------------------------------------------------------
                case FileUpload:

                    System.out.println("\n\n\n\n\n");
                    print_files();
                    System.out.println("\nEnter a file path to add (or type \"back\" to return to main menu):");

                    String file_path = input.nextLine();

                    if (file_path.equals("back")) {
                        state = State.MainMenu;
                        break;
                    }

                    File file = new File(file_path);

                    if (!file.exists()) {
                        System.out.println("File not found!!!");
                        break;
                    }

                    upload_files.add(file);

                    break;


                // -----------------------------------------------------------------------------------------------------
                case UploadToGCP:

                    // UPLOAD LIST OF FILE TO GCP
                    for (File f : upload_files) {

                        String projectId = "smart-window-333522";
                        String bucketName = "dataproc-staging-us-central1-1040393033249-fau4yfpx";
                        String objectName = f.getName();
                        String filePath = f.toString();

                        StorageOptions storageOptions = StorageOptions.newBuilder()
                                .setProjectId(projectId)
                                .setCredentials(GoogleCredentials.fromStream(new
                                        FileInputStream("/Users/connorhalloran/Desktop/smart-window-333522-99e1ae519bc2.json"))).build();

                        Storage storage = storageOptions.getService();
                        BlobId blobId = BlobId.of(bucketName, objectName);
                        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
                        storage.create(blobInfo, Files.readAllBytes(Paths.get(filePath)));

                        System.out.println(
                                "File " + filePath + " uploaded to bucket " + bucketName + " as " + objectName);
                    }

                    state = State.SuccessfulProcessing;

                    break;



                // -----------------------------------------------------------------------------------------------------
                case SuccessfulProcessing:

                    System.out.println("\n\n\n\n\nSelect option:");
                    System.out.println(" 1. Search For Term");
                    System.out.println(" 2. Top-N");

                    choice = input.nextInt();
                    input.nextLine(); // Fixes bug

                    switch (choice) {
                        case 1:
                            state = State.SearchForTerm;
                            break;
                        case 2:
                            state = State.TopN;
                            break;
                    }
                    break;



                // -----------------------------------------------------------------------------------------------------
                case SearchForTerm:

                    System.out.println("\n\n\n\n\nEnter search term:");

                    String search_term = input.nextLine();

                    /*
                     *
                     * SEND SEARCH TERM TO GCP PROGRAM
                     *
                     */

                    /*
                     *
                     * GET BACK FORMATTED RESULTS STRING FROM GCP
                     *
                     */

                    System.out.println("\n\n\n\n\nResults for search term \"" + search_term + "\":");

                    /*
                     *
                     * PRINT RESULTS HERE
                     *
                     */

                    System.out.println("Press Return to continue.");
                    input.nextLine();
                    state = State.SuccessfulProcessing;

                    break;



                // -----------------------------------------------------------------------------------------------------
                case TopN:

                    System.out.println("\n\n\n\n\nEnter N Value:");

                    int n = input.nextInt();
                    input.nextLine(); // Fixes bug

                    /*
                     *
                     * SEND N VALUE TO GCP PROGRAM
                     *
                     */

                    /*
                     *
                     * RECEIVE FORMATTED RESULTS STRING FROM GCP
                     *
                     */

                    System.out.println("\n\n\n\n\nTop " + n + " results:");

                    /*
                     *
                     * PRINT OUT RESULTS STRING FROM GCP
                     *
                     */

                    System.out.println("Press Return to continue.");
                    input.nextLine();
                    state = State.SuccessfulProcessing;
                    break;
            }
        }
    }

    public static void print_files() {
        System.out.println("\nFiles ready to upload:");
        for (File f : upload_files) {
            System.out.println(f.getName());
        }
    }
}