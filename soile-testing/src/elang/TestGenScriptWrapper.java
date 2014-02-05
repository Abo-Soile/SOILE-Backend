package elang;

import java.io.IOException;


class TestGenScriptWrapper {


    // Test instruction set generation, using aboslute paths for now
    public static void main(String[] args) throws IOException {
        TestGenScript test = new TestGenScript();

        //This line should be edited to the project roots path, ugly hack :S
        String basePath = "/home/danno/SoileIDEA/soile2";

        //String tmplFilename = "/Users/tuope/programming/soile/prod/mods/fi.abo.kogni.soile~experiment-lang~1.0/templates/elang.stg";
        //String tmplFilename = "C:/Dropbox/DevStuff/SoileIDEA/soile2/soile/prod/mods/fi.abo.kogni.soile~experiment-lang~1.0/templates/elang.stg";
        String tmplFilename = basePath + "/prod/mods/fi.abo.kogni.soile~experiment-lang~1.0/templates/elang.stg";
        test.setTemplate(tmplFilename);
        //String inputFilename = "/Users/tuope/programming/soile/explang/demoinput008";
        //String inputFilename = "C:/Dropbox/DevStuff/SoileIDEA/soile2/soile/explang/demoinput008";
        String inputFilename = basePath + "/soile/explang/demoinput008";
        test.setInputFile(inputFilename);
        test.run();
    }

}