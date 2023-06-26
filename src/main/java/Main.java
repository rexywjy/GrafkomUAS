import Engine.*;
import Engine.Object;
import org.joml.*;
import org.lwjgl.opengl.GL;

import java.io.IOException;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;

public class Main {
    private Window window = new Window(1600, 950, "Crime City");
    ArrayList<Object> rexyObject = new ArrayList<>();
    ArrayList<Object> playerTank = new ArrayList<>();
    ArrayList<Object> orangAneh = new ArrayList<>();
    // 0: car
    ArrayList<Object> lightsRex = new ArrayList<>();
    ArrayList<Object> lights = new ArrayList<>();
    ArrayList<Object> objectGround = new ArrayList<>();
    ArrayList<Object> playerBotak = new ArrayList<>();
    ArrayList<Object> playerCar = new ArrayList<>();
    ArrayList<Object> playerCopCar = new ArrayList<>();

    ArrayList<Object> cityPoly = new ArrayList<>();


    Vector4f warnaBush = new Vector4f(51 / 255f, 117 / 255f, 69 / 255f, 1.0f);
    Vector4f warnaRerumputan = new Vector4f(76 / 255f, 175 / 255f, 25 / 255f, 1.0f);

    Camera camera = new Camera();
    Projection projection = new Projection(window.getWidth(), window.getHeight());
    float distance = 3f;
    float angle = 0f;
    float rotation = (float)Math.toRadians(1f);
    float move = 0.01f;
    List<Float> copPosition;
    boolean dark = true;
    int carPos = 0;
    int modeToggle = 0;

    public void run() throws IOException {

        init();
        loop();

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    public void init() throws IOException {
        window.init();
        GL.createCapabilities();
        camera.setPosition(-1.7f, 1f, 2.5f + distance);

        List hem = Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
        );


        List hem2 = Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene2.frag",
                        GL_FRAGMENT_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene2.vert",
                        GL_VERTEX_SHADER)
        );


        // samantha
        {
            orangAneh.add(new Model(
                    hem,
                    new ArrayList<>(),
//                new Vector4f(0.5f,0.f,0.f,1.0f),
                    new Vector4f(255 / 255f, 255 / 255f, 51 / 255f, 1.0f),
                    "resources/model/samantha/orang.obj"
            ));
            orangAneh.get(0).scaleObject(0.1f);
            orangAneh.get(0).translateObject(0.5f, 0.0f, 0.1f);
            orangAneh.get(0).rotateObject((float)Math.toRadians(180f),0f,1f,0f);

            orangAneh.add(new Model(
                    hem,
                    new ArrayList<>(),
//                new Vector4f(0.5f,0.f,0.f,1.0f),
                    new Vector4f(204 / 255f, 102 / 255f, 0 / 255f, 1.0f),
                    "resources/model/samantha/orang.obj"
            ));
            orangAneh.get(1).scaleObject(0.1f);
            orangAneh.get(1).translateObject(0.5f, 0.0f, 0.1f);

            orangAneh.add(new Model(
                    hem,
                    new ArrayList<>(),
//                new Vector4f(0.5f,0.f,0.f,1.0f),
                    new Vector4f(255 / 255f, 0 / 255f, 0 / 255f, 1.0f),
                    "resources/model/samantha/orang.obj"
            ));
            orangAneh.get(2).scaleObject(0.1f);
            orangAneh.get(2).translateObject(0.7f, 0.0f, 0.7f);
            orangAneh.get(2).rotateObject((float)Math.toRadians(90f),0f,1f,0f);

            orangAneh.add(new Model(
                    hem,
                    new ArrayList<>(),
//                new Vector4f(0.5f,0.f,0.f,1.0f),
                    new Vector4f(0 / 255f, 204 / 255f, 0 / 255f, 1.0f),
                    "resources/model/samantha/orang.obj"
            ));
            orangAneh.get(3).scaleObject(0.1f);
            orangAneh.get(3).translateObject(0.7f, 0.0f, 0.7f);

            orangAneh.add(new Model(
                    hem,
                    new ArrayList<>(),
//                new Vector4f(0.5f,0.f,0.f,1.0f),
                    new Vector4f(0 / 255f, 128 / 255f, 255 / 255f, 1.0f),
                    "resources/model/samantha/orang.obj"
            ));
            orangAneh.get(4).scaleObject(0.1f);
            orangAneh.get(4).translateObject(-1.0f, 0.0f, 0.7f);

            orangAneh.add(new Model(
                    hem,
                    new ArrayList<>(),
//                new Vector4f(0.5f,0.f,0.f,1.0f),
                    new Vector4f(153 / 255f, 51 / 255f, 255 / 255f, 1.0f),
                    "resources/model/samantha/orang.obj"
            ));
            orangAneh.get(5).scaleObject(0.1f);
            orangAneh.get(5).translateObject(-1.0f, 0.0f, 0.8f);
            orangAneh.get(5).rotateObject((float)Math.toRadians(60f),0f,1f,0f);

            orangAneh.add(new Model(
                    hem,
                    new ArrayList<>(),
//                new Vector4f(0.5f,0.f,0.f,1.0f),
                    new Vector4f(255 / 255f, 102 / 255f, 255 / 255f, 1.0f),
                    "resources/model/samantha/orang.obj"
            ));
            orangAneh.get(6).scaleObject(0.1f);
            orangAneh.get(6).translateObject(-1.0f, 0.0f, 2.0f);
            orangAneh.get(6).rotateObject((float)Math.toRadians(60f),0f,1f,0f);

            orangAneh.add(new Model(
                    hem,
                    new ArrayList<>(),
//                new Vector4f(0.5f,0.f,0.f,1.0f),
                    new Vector4f(0 / 255f, 0 / 255f, 0 / 255f, 1.0f),
                    "resources/model/samantha/orang.obj"
            ));
            orangAneh.get(7).scaleObject(0.1f);
            orangAneh.get(7).translateObject(-1.0f, 0.0f, 5.0f);
        } // samantha



        // angie
        {
            // Lampu Angie
            lights.add(new Model(
                    hem,
                    new ArrayList<>(),
                    new Vector4f(255f / 255f, 240 / 255f, 250 / 255f, 1.0f),
                    new ArrayList<>(List.of(2f,2f,4.3f)) // ini adalah centerpoint
            ));
            // Tank
            float r = 0.055f;
            float g = 0.099f;
            float b = 0.023f;
            // kepala
            playerTank.add(new Model(
                    hem,
                    new ArrayList<>(),
                    new Vector4f(r, g, b, 1.0f),
                    "resources/model/angie/head.obj"

            ));

            // badan
            r = 0.037f;
            g = 0.070f;
            b = 0.015f;
            playerTank.get(0).getChildObject().add(new Model(
                    hem,
                    new ArrayList<>(),
                    new Vector4f(r, g, b, 1.0f),
                    "resources/model/angie/hull2.obj"
            ));

            // wheel
            r = 0.118f;
            g = 0.137f;
            b = 0.116f;
            playerTank.get(0).getChildObject().add(new Model(
                    hem,
                    new ArrayList<>(),
                    new Vector4f(r, g, b, 1.0f),
                    "resources/model/angie/wheel2.obj"

            ));

            playerTank.get(0).scaleObject(0.1f, 0.1f, 0.1f);
//                playerTank.get(i).moveObject(0.0f, 0.0f, -0.1f);
            playerTank.get(0).translateObject(-0.0f, 0.0f, -0.5f);



            playerTank.get(0).getChildObject().get(0).scaleObject(0.1f, 0.1f, 0.1f);
            playerTank.get(0).getChildObject().get(0).translateObject(-0.0f, 0.0f, -0.5f);
            playerTank.get(0).getChildObject().get(1).scaleObject(0.1f, 0.1f, 0.1f);
            playerTank.get(0).getChildObject().get(1).translateObject(-0.0f, 0.0f, -0.5f);


            for (int counterr=0 ; counterr < playerTank.size() ; counterr++) {
                playerTank.get(counterr).translateObject(-0.2f, -0.06f, 0f);
                playerTank.get(counterr).rotateObject((float)Math.toRadians(0f),0f,1f,0f);
            }

        }
        // angie

        {
            lightsRex.add(new Model( // lampu 1
                    hem,
                    new ArrayList<>(),
                    new Vector4f(255 / 255f, 237 / 255f, 0 / 255f, 1.0f),
                    "resources/model/rexy/envi/lampu/streetlamp001.obj"
            ));

            lightsRex.get(0).scaleObject(0.15f);
            lightsRex.get(0).translateObject(-2.7f, -0.843f, 0f);

            lightsRex.add(new Model( // lampu 2
                    hem,
                    new ArrayList<>(),
//                new Vector4f(0.5f,0.f,0.f,1.0f),
                    new Vector4f(255 / 255f, 237 / 255f, 0 / 255f, 1.0f),
                    "resources/model/rexy/envi/lampu/KUNINGstreetlamp.obj"
            ));

            lightsRex.get(1).scaleObject(0.15f);
            lightsRex.get(1).translateObject(-2.7f, -0.843f, 0f);

            lightsRex.add(new Model( // lampu 3  MATI LAMPU
                    hem,
                    new ArrayList<>(),
//                new Vector4f(0.5f,0.f,0.f,1.0f),
                    new Vector4f(113 / 255f, 105 / 255f, 0 / 255f, 1.0f),
                    "resources/model/rexy/envi/lampu/ball kuning.obj"
            ));

            lightsRex.get(2).scaleObject(0.15f);
            lightsRex.get(2).translateObject(-2.85f, -0.843f, 2.18f);

            lightsRex.add(new Model( // lampu 4
                    hem,
                    new ArrayList<>(),
//                new Vector4f(0.5f,0.f,0.f,1.0f),
                    new Vector4f(255 / 255f, 237 / 255f, 0 / 255f, 1.0f),
                    "resources/model/rexy/envi/lampu/ball kuning.obj"
            ));

            lightsRex.get(3).scaleObject(0.15f);
            lightsRex.get(3).translateObject(-2.7f, -0.843f, 0f);


            lightsRex.add(new Model( // lampu 5
                    hem,
                    new ArrayList<>(),
//                new Vector4f(0.5f,0.f,0.f,1.0f),
                    new Vector4f(255 / 255f, 237 / 255f, 0 / 255f, 1.0f),
                    "resources/model/rexy/envi/lampu/ball kuning.obj"
            ));

            lightsRex.get(4).scaleObject(0.15f);
            lightsRex.get(4).translateObject(-9.27f, -0.843f, -3.571f);

            lightsRex.add(new Model( // lampu 6
                    hem,
                    new ArrayList<>(),
//                new Vector4f(0.5f,0.f,0.f,1.0f),
                    new Vector4f(255 / 255f, 237 / 255f, 0 / 255f, 1.0f),
                    "resources/model/rexy/envi/lampu/ball kuning.obj"
            ));

            lightsRex.get(5).scaleObject(0.15f);
            lightsRex.get(5).translateObject(-3.851f, -0.843f, -5.11f);

        } // lampu jalan

        {
            // main + garden
            {
                cityPoly.add(new Model( // base jalan aspal abu tua
                        hem,
                        new ArrayList<>(),
//                new Vector4f(0.5f,0.f,0.f,1.0f),
                        new Vector4f(79 / 255f, 79 / 255f, 79 / 255f, 1.0f),
                        "resources/model/rexy/envi/aspal-ground2.obj"
                ));

                cityPoly.get(0).scaleObject(0.15f);
                cityPoly.get(0).translateObject(-2.7f, -0.843f, 0f);


                cityPoly.get(0).getChildObject().add(new Model( // awan
                        hem,
                        new ArrayList<>(),
//                new Vector4f(0.5f,0.f,0.f,1.0f),
                        new Vector4f(223 / 255f, 223 / 255f, 231 / 255f, 1.0f),
                        "resources/model/rexy/envi/awanawan.obj"
                ));

                cityPoly.get(0).getChildObject().get(0).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(0).translateObject(-2.7f, -1.843f, 0f);


                cityPoly.get(0).getChildObject().add(new Model( // airnya air mancur
                        hem,
                        new ArrayList<>(),
//                new Vector4f(0.5f,0.f,0.f,1.0f),
                        new Vector4f(102 / 255f, 213 / 255f, 255 / 255f, 1.0f),
                        "resources/model/rexy/envi/water-fountain.obj"
                ));

                cityPoly.get(0).getChildObject().get(1).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(1).translateObject(-2.7f, -0.843f, 0f);

                cityPoly.get(0).getChildObject().add(new Model( // alasnya air mancur
                        hem,
                        new ArrayList<>(),
//                new Vector4f(0.5f,0.f,0.f,1.0f),
                        new Vector4f(174 / 255f, 165 / 255f, 184 / 255f, 1.0f),
                        "resources/model/rexy/envi/alas-fountain.obj"
                ));

                cityPoly.get(0).getChildObject().get(2).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(2).translateObject(-2.7f, -0.843f, 0f);

                cityPoly.get(0).getChildObject().add(new Model( // atasnya air mancur
                        hem,
                        new ArrayList<>(),
//                new Vector4f(0.5f,0.f,0.f,1.0f),
                        new Vector4f(121 / 255f, 115 / 255f, 128 / 255f, 1.0f),
                        "resources/model/rexy/envi/atas-fountain.obj"
                ));

                cityPoly.get(0).getChildObject().get(3).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(3).translateObject(-2.7f, -0.843f, 0f);


                cityPoly.get(0).getChildObject().add(new Model( // border garden
                        hem,
                        new ArrayList<>(),
//                new Vector4f(0.5f,0.f,0.f,1.0f),
                        new Vector4f(135 / 255f, 147 / 255f, 154 / 255f, 1.0f),
                        "resources/model/rexy/envi/border garden.obj"
                ));

                cityPoly.get(0).getChildObject().get(4).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(4).translateObject(-2.7f, -0.84f, 0f);


                cityPoly.get(0).getChildObject().add(new Model( // tanah ijo garden
                        hem,
                        new ArrayList<>(),
//                new Vector4f(0.5f,0.f,0.f,1.0f),
                        new Vector4f(49 / 255f, 112 / 255f, 66 / 255f, 1.0f),
                        "resources/model/rexy/envi/green-relief.obj"
                ));

                cityPoly.get(0).getChildObject().get(5).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(5).translateObject(-2.7f, -0.843f, 0f);

                cityPoly.get(0).getChildObject().add(new Model( // tembok pager
                        hem,
                        new ArrayList<>(),
//                new Vector4f(0.5f,0.f,0.f,1.0f),
                        new Vector4f(136 / 255f, 119 / 255f, 118 / 255f, 1.0f),
                        "resources/model/rexy/envi/tembok pager.obj"
                ));

                cityPoly.get(0).getChildObject().get(6).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(6).translateObject(-2.7f, -0.843f, 0f);

                cityPoly.get(0).getChildObject().add(new Model( // pager
                        hem,
                        new ArrayList<>(),
//                new Vector4f(0.5f,0.f,0.f,1.0f),
                        new Vector4f(206 / 255f, 155 / 255f, 101 / 255f, 1.0f),
                        "resources/model/rexy/envi/coklat pager kursi garden.obj"
                ));

                cityPoly.get(0).getChildObject().get(7).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(7).translateObject(-2.7f, -0.843f, 0f);


                cityPoly.get(0).getChildObject().add(new Model( // lampu
                        hem,
                        new ArrayList<>(),
//                new Vector4f(0.5f,0.f,0.f,1.0f),
                        new Vector4f(43 / 255f, 43 / 255f, 43 / 255f, 1.0f),
                        "resources/model/rexy/envi/lampu taman.obj"
                ));

                cityPoly.get(0).getChildObject().get(8).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(8).translateObject(-2.7f, -0.843f, 0f);

                cityPoly.get(0).getChildObject().add(new Model( // bush, pohon garden
                        hem,
                        new ArrayList<>(),
//                new Vector4f(0.5f,0.f,0.f,1.0f),
                        new Vector4f(48 / 255f, 76 / 255f, 50 / 255f, 1.0f),
                        "resources/model/rexy/envi/garden bush.obj"
                ));

                cityPoly.get(0).getChildObject().get(9).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(9).translateObject(-2.7f, -0.843f, 0f);

                cityPoly.get(0).getChildObject().add(new Model( // batang pohon garden
                        hem,
                        new ArrayList<>(),
//                new Vector4f(0.5f,0.f,0.f,1.0f),
                        new Vector4f(120 / 255f, 61 / 255f, 28 / 255f, 1.0f),
                        "resources/model/rexy/envi/batang pohon.obj"
                ));

                cityPoly.get(0).getChildObject().get(10).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(10).translateObject(-2.7f, -0.843f, 0f);


                cityPoly.get(0).getChildObject().add(new Model( // tempat sampah di garden
                        hem,
                        new ArrayList<>(),
//                new Vector4f(0.5f,0.f,0.f,1.0f),
                        new Vector4f(42 / 255f, 72 / 255f, 229 / 255f, 1.0f),
                        "resources/model/rexy/envi/trash bin.obj"
                ));

                cityPoly.get(0).getChildObject().get(11).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(11).translateObject(-2.7f, -0.843f, 0f);
            } // main + garden

            // trio ground get(0).get(12) 0-7
            {
                cityPoly.get(0).getChildObject().add(new Model( // alas trio ground
                        hem,
                        new ArrayList<>(),
//                new Vector4f(0.5f,0.f,0.f,1.0f),
                        new Vector4f(63 / 255f, 83 / 255f, 51 / 255f, 1.0f),
                        "resources/model/rexy/envi/trio gedung/tanah e.obj"
                ));

                cityPoly.get(0).getChildObject().get(12).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(12).translateObject(-2.7f, -0.84f, 0f);


                cityPoly.get(0).getChildObject().get(12).getChildObject().add(new Model( // atap
                        hem,
                        new ArrayList<>(),
//                new Vector4f(0.5f,0.f,0.f,1.0f),
                        new Vector4f(102 / 255f, 104 / 255f, 104 / 255f, 1.0f),
                        "resources/model/rexy/envi/trio gedung/gedung01atap.obj"
                ));

                cityPoly.get(0).getChildObject().get(12).getChildObject().get(0).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(12).getChildObject().get(0).translateObject(-2.7f, -0.843f, 0f);

                cityPoly.get(0).getChildObject().get(12).getChildObject().add(new Model( // tembok
                        hem,
                        new ArrayList<>(),
//                new Vector4f(0.5f,0.f,0.f,1.0f),
                        new Vector4f(233 / 255f, 46 / 255f, 52 / 255f, 1.0f),
                        "resources/model/rexy/envi/trio gedung/gedung01tembok.obj"
                ));

                cityPoly.get(0).getChildObject().get(12).getChildObject().get(1).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(12).getChildObject().get(1).translateObject(-2.7f, -0.843f, 0f);


                cityPoly.get(0).getChildObject().get(12).getChildObject().add(new Model( // jendela
                        hem,
                        new ArrayList<>(),
//                new Vector4f(0.5f,0.f,0.f,1.0f),
                        new Vector4f(166 / 255f, 202 / 255f, 220 / 255f, 1.0f),
                        "resources/model/rexy/envi/trio gedung/gedung01jendelaAC.obj"
                ));

                cityPoly.get(0).getChildObject().get(12).getChildObject().get(2).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(12).getChildObject().get(2).translateObject(-2.7f, -0.843f, 0f);


                cityPoly.get(0).getChildObject().get(12).getChildObject().add(new Model( // rumpute
                        hem,
                        new ArrayList<>(),
                        warnaBush,
                        "resources/model/rexy/envi/trio gedung/rumpute.obj"
                ));

                cityPoly.get(0).getChildObject().get(12).getChildObject().get(3).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(12).getChildObject().get(3).translateObject(-2.7f, -0.843f, 0f);


                // gedung 3

                cityPoly.get(0).getChildObject().get(12).getChildObject().add(new Model( // tembok
                        hem,
                        new ArrayList<>(),
//                new Vector4f(0.5f,0.f,0.f,1.0f),
                        new Vector4f(69 / 255f, 98 / 255f, 225 / 255f, 1.0f),
                        "resources/model/rexy/envi/trio gedung/gedung03tembok.obj"
                ));

                cityPoly.get(0).getChildObject().get(12).getChildObject().get(4).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(12).getChildObject().get(4).translateObject(-2.7f, -0.843f, 0f);


                cityPoly.get(0).getChildObject().get(12).getChildObject().add(new Model( // jendela
                        hem,
                        new ArrayList<>(),
//                new Vector4f(0.5f,0.f,0.f,1.0f),
                        new Vector4f(166 / 255f, 202 / 255f, 220 / 255f, 1.0f),
                        "resources/model/rexy/envi/trio gedung/gedung03door.obj"
                ));

                cityPoly.get(0).getChildObject().get(12).getChildObject().get(5).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(12).getChildObject().get(5).translateObject(-2.7f, -0.843f, 0f);

                cityPoly.get(0).getChildObject().get(12).getChildObject().add(new Model( // balkon
                        hem,
                        new ArrayList<>(),
//                new Vector4f(0.5f,0.f,0.f,1.0f),
                        new Vector4f(130 / 255f, 137 / 255f, 141 / 255f, 1.0f),
                        "resources/model/rexy/envi/trio gedung/gedung03balkon.obj"
                ));

                cityPoly.get(0).getChildObject().get(12).getChildObject().get(6).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(12).getChildObject().get(6).translateObject(-2.7f, -0.843f, 0f);


                cityPoly.get(0).getChildObject().get(12).getChildObject().add(new Model( // atap
                        hem,
                        new ArrayList<>(),
//                new Vector4f(0.5f,0.f,0.f,1.0f),
                        new Vector4f(68 / 255f, 68 / 255f, 68 / 255f, 1.0f),
                        "resources/model/rexy/envi/trio gedung/gedung03atap.obj"
                ));

                cityPoly.get(0).getChildObject().get(12).getChildObject().get(7).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(12).getChildObject().get(7).translateObject(-2.7f, -0.843f, 0f);






            }
            // trio gedung get(0).get(12) 0-7


            // bank get 0 get 13
            {
                cityPoly.get(0).getChildObject().add(new Model( // alas bank
                        hem,
                        new ArrayList<>(),
                        warnaRerumputan,
                        "resources/model/rexy/envi/bank/bank alas.obj"
                ));

                cityPoly.get(0).getChildObject().get(13).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(13).translateObject(-2.7f, -0.84f, 0f);



                cityPoly.get(0).getChildObject().get(13).getChildObject().add(new Model( // gedung
                        hem,
                        new ArrayList<>(),
                        new Vector4f(241 / 255f, 211 / 255f, 203 / 255f, 1.0f),
                        "resources/model/rexy/envi/bank/bank gedung.obj"
                ));
                cityPoly.get(0).getChildObject().get(13).getChildObject().get(0).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(13).getChildObject().get(0).translateObject(-2.7f, -0.843f, 0f);


                cityPoly.get(0).getChildObject().get(13).getChildObject().add(new Model( // atap1
                        hem,
                        new ArrayList<>(),
                        new Vector4f(190 / 255f, 166 / 255f, 160 / 255f, 1.0f),
                        "resources/model/rexy/envi/bank/bank atap.obj"
                ));
                cityPoly.get(0).getChildObject().get(13).getChildObject().get(1).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(13).getChildObject().get(1).translateObject(-2.7f, -0.843f, 0f);


                cityPoly.get(0).getChildObject().get(13).getChildObject().add(new Model( // atap2
                        hem,
                        new ArrayList<>(),
                        new Vector4f(190 / 255f, 166 / 255f, 160 / 255f, 1.0f),
                        "resources/model/rexy/envi/bank/bank atap2.obj"
                ));
                cityPoly.get(0).getChildObject().get(13).getChildObject().get(2).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(13).getChildObject().get(2).translateObject(-2.7f, -0.843f, 0f);

                cityPoly.get(0).getChildObject().get(13).getChildObject().add(new Model( // bush
                        hem,
                        new ArrayList<>(),
                        warnaBush,
                        "resources/model/rexy/envi/bank/bank bush.obj"
                ));
                cityPoly.get(0).getChildObject().get(13).getChildObject().get(3).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(13).getChildObject().get(3).translateObject(-2.7f, -0.843f, 0f);


                cityPoly.get(0).getChildObject().get(13).getChildObject().add(new Model( // carpet
                        hem,
                        new ArrayList<>(),
                        new Vector4f(147 / 255f, 0 / 255f, 0 / 255f, 1.0f),
                        "resources/model/rexy/envi/bank/bank carpet.obj"
                ));
                cityPoly.get(0).getChildObject().get(13).getChildObject().get(4).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(13).getChildObject().get(4).translateObject(-2.7f, -0.843f, 0f);

                cityPoly.get(0).getChildObject().get(13).getChildObject().add(new Model( // dollar
                        hem,
                        new ArrayList<>(),
                        new Vector4f(0 / 255f, 241 / 255f, 0 / 255f, 1.0f),
                        "resources/model/rexy/envi/bank/bank dollar.obj"
                ));
                cityPoly.get(0).getChildObject().get(13).getChildObject().get(5).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(13).getChildObject().get(5).translateObject(-2.7f, -0.843f, 0f);

                cityPoly.get(0).getChildObject().get(13).getChildObject().add(new Model( // flat
                        hem,
                        new ArrayList<>(),
                        new Vector4f(190 / 255f, 166 / 255f, 160 / 255f, 1.0f),
                        "resources/model/rexy/envi/bank/bank flat.obj"
                ));
                cityPoly.get(0).getChildObject().get(13).getChildObject().get(6).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(13).getChildObject().get(6).translateObject(-2.7f, -0.843f, 0f);

                cityPoly.get(0).getChildObject().get(13).getChildObject().add(new Model( // frame
                        hem,
                        new ArrayList<>(),
                        new Vector4f(190 / 255f, 166 / 255f, 160 / 255f, 1.0f),
                        "resources/model/rexy/envi/bank/bank frame.obj"
                ));
                cityPoly.get(0).getChildObject().get(13).getChildObject().get(7).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(13).getChildObject().get(7).translateObject(-2.7f, -0.843f, 0f);

                cityPoly.get(0).getChildObject().get(13).getChildObject().add(new Model( // jendela
                        hem,
                        new ArrayList<>(),
                        new Vector4f(214 / 255f, 218 / 255f, 218 / 255f, 1.0f),
                        "resources/model/rexy/envi/bank/bank jendela.obj"
                ));
                cityPoly.get(0).getChildObject().get(13).getChildObject().get(8).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(13).getChildObject().get(8).translateObject(-2.7f, -0.843f, 0f);

                cityPoly.get(0).getChildObject().get(13).getChildObject().add(new Model( // lamp
                        hem,
                        new ArrayList<>(),
                        new Vector4f(12 / 255f, 12 / 255f, 12 / 255f, 1.0f),
                        "resources/model/rexy/envi/bank/bank lamp.obj"
                ));
                cityPoly.get(0).getChildObject().get(13).getChildObject().get(9).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(13).getChildObject().get(9).translateObject(-2.7f, -0.843f, 0f);

                cityPoly.get(0).getChildObject().get(13).getChildObject().add(new Model( // pillar
                        hem,
                        new ArrayList<>(),
                        new Vector4f(190 / 255f, 166 / 255f, 160 / 255f, 1.0f),
                        "resources/model/rexy/envi/bank/bank pillar.obj"
                ));
                cityPoly.get(0).getChildObject().get(13).getChildObject().get(10).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(13).getChildObject().get(10).translateObject(-2.7f, -0.843f, 0f);

                cityPoly.get(0).getChildObject().get(13).getChildObject().add(new Model( // plant
                        hem,
                        new ArrayList<>(),
                        warnaRerumputan,
                        "resources/model/rexy/envi/bank/bank plant.obj"
                ));
                cityPoly.get(0).getChildObject().get(13).getChildObject().get(11).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(13).getChildObject().get(11).translateObject(-2.7f, -0.843f, 0f);

                cityPoly.get(0).getChildObject().get(13).getChildObject().add(new Model( // pot
                        hem,
                        new ArrayList<>(),
                        new Vector4f(170 / 255f, 83 / 255f, 68 / 255f, 1.0f),
                        "resources/model/rexy/envi/bank/bank pot.obj"
                ));
                cityPoly.get(0).getChildObject().get(13).getChildObject().get(12).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(13).getChildObject().get(12).translateObject(-2.7f, -0.843f, 0f);

                cityPoly.get(0).getChildObject().get(13).getChildObject().add(new Model( // tangga
                        hem,
                        new ArrayList<>(),
                        new Vector4f(241 / 255f, 211 / 255f, 203 / 255f, 1.0f),
                        "resources/model/rexy/envi/bank/bank tangga.obj"
                ));
                cityPoly.get(0).getChildObject().get(13).getChildObject().get(13).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(13).getChildObject().get(13).translateObject(-2.7f, -0.843f, 0f);

                cityPoly.get(0).getChildObject().get(13).getChildObject().add(new Model( // bank
                        hem,
                        new ArrayList<>(),
                        new Vector4f(0 / 255f, 241 / 255f, 0 / 255f, 1.0f),
                        "resources/model/rexy/envi/bank/bank.obj"
                ));
                cityPoly.get(0).getChildObject().get(13).getChildObject().get(14).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(13).getChildObject().get(14).translateObject(-2.7f, -0.843f, 0f);






            }
            // bank get 0 get 13

            // lampu additional

            cityPoly.get(0).getChildObject().add(new Model( // lampu
                    hem,
                    new ArrayList<>(),
//                new Vector4f(0.5f,0.f,0.f,1.0f),
                    new Vector4f(43 / 255f, 43 / 255f, 43 / 255f, 1.0f),
                    "resources/model/rexy/envi/lampu taman2.obj"
            ));

            cityPoly.get(0).getChildObject().get(14).scaleObject(0.15f);
            cityPoly.get(0).getChildObject().get(14).translateObject(-2.7f, -0.843f, 0f);
            cityPoly.get(0).getChildObject().add(new Model( // lampu
                    hem,
                    new ArrayList<>(),
//                new Vector4f(0.5f,0.f,0.f,1.0f),
                    new Vector4f(43 / 255f, 43 / 255f, 43 / 255f, 1.0f),
                    "resources/model/rexy/envi/lampu taman3.obj"
            ));

            cityPoly.get(0).getChildObject().get(15).scaleObject(0.15f);
            cityPoly.get(0).getChildObject().get(15).translateObject(-2.7f, -0.843f, 0f);




            cityPoly.get(0).getChildObject().add(new Model( // ALAS IJO
                    hem,
                    new ArrayList<>(),
//                new Vector4f(0.5f,0.f,0.f,1.0f),
                    warnaRerumputan,
                    "resources/model/rexy/envi/alas ijo.obj"
            ));

            cityPoly.get(0).getChildObject().get(16).scaleObject(0.15f);
            cityPoly.get(0).getChildObject().get(16).translateObject(-2.7f, -0.84f, 0f);

            cityPoly.get(0).getChildObject().add(new Model( // tepi aspal
                    hem,
                    new ArrayList<>(),
                    new Vector4f(113/225f,113/225f,113/225f,1.0f),
                    "resources/model/rexy/envi/tepi aspal.obj"
            ));

            cityPoly.get(0).getChildObject().get(17).scaleObject(0.15f);
            cityPoly.get(0).getChildObject().get(17).translateObject(-2.7f, -0.84f, 0f);

            cityPoly.get(0).getChildObject().add(new Model( // sudut aspal
                    hem,
                    new ArrayList<>(),
                    warnaRerumputan,
                    "resources/model/rexy/envi/sudut ijo.obj"
            ));

            cityPoly.get(0).getChildObject().get(18).scaleObject(0.15f);
            cityPoly.get(0).getChildObject().get(18).translateObject(-2.7f, -0.84f, 0f);

            // shopping mall 19 get 0 - 8
            {
                cityPoly.get(0).getChildObject().add(new Model( // sudut aspal
                        hem,
                        new ArrayList<>(),
                        new Vector4f(230/225f,101/225f,39/225f,1.0f),
                        "resources/model/rexy/envi/shop/main.obj"
                ));

                cityPoly.get(0).getChildObject().get(19).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(19).translateObject(-2.7f, -0.84f, 0f);


                cityPoly.get(0).getChildObject().get(19).getChildObject().add(new Model( // sudut
                        hem,
                        new ArrayList<>(),
                        new Vector4f(200/225f,46/225f,34/225f,1.0f),
                        "resources/model/rexy/envi/shop/main tengah.obj"
                ));

                cityPoly.get(0).getChildObject().get(19).getChildObject().get(0).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(19).getChildObject().get(0).translateObject(-2.7f,
                    -0.84f, 0f);

                cityPoly.get(0).getChildObject().get(19).getChildObject().add(new Model( // jendela
                        hem,
                        new ArrayList<>(),
                        new Vector4f(120/225f,192/225f,194/225f,1.0f),
                        "resources/model/rexy/envi/shop/jendela.obj"
                ));

                cityPoly.get(0).getChildObject().get(19).getChildObject().get(1).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(19).getChildObject().get(1).translateObject(-2.7f,
                        -0.84f, 0f);

                cityPoly.get(0).getChildObject().get(19).getChildObject().add(new Model( // atap
                        hem,
                        new ArrayList<>(),
                        new Vector4f(206/225f,206/225f,206/225f,1.0f),
                        "resources/model/rexy/envi/shop/atap.obj"
                ));

                cityPoly.get(0).getChildObject().get(19).getChildObject().get(2).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(19).getChildObject().get(2).translateObject(-2.7f,
                        -0.84f, 0f);

                cityPoly.get(0).getChildObject().get(19).getChildObject().add(new Model( // bush
                        hem,
                        new ArrayList<>(),
                        warnaBush,
                        "resources/model/rexy/envi/shop/bush.obj"
                ));

                cityPoly.get(0).getChildObject().get(19).getChildObject().get(3).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(19).getChildObject().get(3).translateObject(-2.7f,
                        -0.84f, 0f);

                cityPoly.get(0).getChildObject().get(19).getChildObject().add(new Model( // pot
                        hem,
                        new ArrayList<>(),
                        new Vector4f(149/225f,82/225f,44/225f,1.0f),
                        "resources/model/rexy/envi/shop/pot.obj"
                ));

                cityPoly.get(0).getChildObject().get(19).getChildObject().get(4).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(19).getChildObject().get(4).translateObject(-2.7f,
                        -0.84f, 0f);

                cityPoly.get(0).getChildObject().get(19).getChildObject().add(new Model( // payung
                        hem,
                        new ArrayList<>(),
                        new Vector4f(241/225f,185/225f,127/225f,1.0f),
                        "resources/model/rexy/envi/shop/payung.obj"
                ));

                cityPoly.get(0).getChildObject().get(19).getChildObject().get(5).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(19).getChildObject().get(5).translateObject(-2.7f,
                        -0.84f, 0f);
                cityPoly.get(0).getChildObject().get(19).getChildObject().add(new Model(
                        // parkiran gedung
                        hem,
                        new ArrayList<>(),
                        new Vector4f(187/225f,187/225f,187/225f,1.0f),
                        "resources/model/rexy/envi/shop/park.obj"
                ));

                cityPoly.get(0).getChildObject().get(19).getChildObject().get(6).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(19).getChildObject().get(6).translateObject(-2.7f,
                        -0.84f, 0f);

                cityPoly.get(0).getChildObject().get(19).getChildObject().add(new Model( // frame
                        hem,
                        new ArrayList<>(),
                        new Vector4f(187/225f,187/225f,187/225f,1.0f),
                        "resources/model/rexy/envi/shop/frame.obj"
                ));

                cityPoly.get(0).getChildObject().get(19).getChildObject().get(7).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(19).getChildObject().get(7).translateObject(-2.7f,
                        -0.84f, 0f);

                cityPoly.get(0).getChildObject().get(19).getChildObject().add(new Model( // frame
                        hem,
                        new ArrayList<>(),
                        new Vector4f(43/225f,43/225f,43/225f,1.0f),
                        "resources/model/rexy/envi/shop/lampu.obj"
                ));

                cityPoly.get(0).getChildObject().get(19).getChildObject().get(8).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(19).getChildObject().get(8).translateObject(-2.7f,
                        -0.84f, 0f);




            }// shopping mall 19 get 0 - 8


            // pom bensin 20 get 0 -
            {

                cityPoly.get(0).getChildObject().add(new Model( // pom
                        hem,
                        new ArrayList<>(),
                        new Vector4f(232/225f,13/225f,0/225f,1.0f),
                        "resources/model/rexy/envi/pom/pom.obj"
                ));
                cityPoly.get(0).getChildObject().get(20).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(20).translateObject(-2.7f, -0.84f, 0f);

                cityPoly.get(0).getChildObject().get(20).getChildObject().add(new Model( // atap
                        hem,
                        new ArrayList<>(),
                        new Vector4f(187/225f,187/225f,187/225f,1.0f),
                        "resources/model/rexy/envi/pom/atap.obj"
                ));
                cityPoly.get(0).getChildObject().get(20).getChildObject().get(0).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(20).getChildObject().get(0).translateObject(-2.7f,
                    -0.84f, 0f);

                cityPoly.get(0).getChildObject().get(20).getChildObject().add(new Model( // tembok
                        hem,
                        new ArrayList<>(),
                        new Vector4f(60/225f,99/225f,162/225f,1.0f),
                        "resources/model/rexy/envi/pom/tembok.obj"
                ));
                cityPoly.get(0).getChildObject().get(20).getChildObject().get(1).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(20).getChildObject().get(1).translateObject(-2.7f,
                        -0.84f, 0f);

                cityPoly.get(0).getChildObject().get(20).getChildObject().add(new Model( // tembok
                        hem,
                        new ArrayList<>(),
                        new Vector4f(43/225f,43/225f,43/225f,1.0f),
                        "resources/model/rexy/envi/pom/roda.obj"
                ));
                cityPoly.get(0).getChildObject().get(20).getChildObject().get(2).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(20).getChildObject().get(2).translateObject(-2.7f,
                        -0.84f, 0f);

                cityPoly.get(0).getChildObject().get(20).getChildObject().add(new Model( // panah
                        hem,
                        new ArrayList<>(),
                        new Vector4f(155/225f,8/225f,0/225f,1.0f),
                        "resources/model/rexy/envi/pom/panah merahj.obj"
                ));
                cityPoly.get(0).getChildObject().get(20).getChildObject().get(3).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(20).getChildObject().get(3).translateObject(-2.7f,
                        -0.84f, 0f);

                cityPoly.get(0).getChildObject().get(20).getChildObject().add(new Model( // pintu
                        hem,
                        new ArrayList<>(),
                        new Vector4f(255/225f,255/225f,255/225f,1.0f),
                        "resources/model/rexy/envi/pom/pintu.obj"
                ));
                cityPoly.get(0).getChildObject().get(20).getChildObject().get(4).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(20).getChildObject().get(4).translateObject(-2.7f,
                        -0.84f, 0f);
                cityPoly.get(0).getChildObject().get(20).getChildObject().add(new Model( // tembak
                        hem,
                        new ArrayList<>(),
                        new Vector4f(255/225f,255/225f,255/225f,1.0f),
                        "resources/model/rexy/envi/pom/tembak.obj"
                ));
                cityPoly.get(0).getChildObject().get(20).getChildObject().get(5).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(20).getChildObject().get(5).translateObject(-2.7f,
                        -0.84f, 0f);
                cityPoly.get(0).getChildObject().get(20).getChildObject().add(new Model( // pohon
                        hem,
                        new ArrayList<>(),
                        warnaBush,
                        "resources/model/rexy/envi/pom/pohon.obj"
                ));
                cityPoly.get(0).getChildObject().get(20).getChildObject().get(6).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(20).getChildObject().get(6).translateObject(-2.7f,
                        -0.84f, 0f);
                cityPoly.get(0).getChildObject().get(20).getChildObject().add(new Model( // jendela
                        hem,
                        new ArrayList<>(),
                        new Vector4f(211/225f,213/225f,218/225f,1.0f),
                        "resources/model/rexy/envi/pom/jendela.obj"
                ));
                cityPoly.get(0).getChildObject().get(20).getChildObject().get(7).scaleObject(0.15f);
                cityPoly.get(0).getChildObject().get(20).getChildObject().get(7).translateObject(-2.7f,
                        -0.84f, 0f);



            }
            // pom bensin 20 get 0 -

        } // environtment

        {
            playerCopCar.add(new Model( // body black
                    hem,
                    new ArrayList<>(),
//                new Vector4f(0.5f,0.f,0.f,1.0f),
                    new Vector4f(22/255f, 22/255f, 22/255f, 0/225f),
                    "resources/model/rexy/cop/body black.obj"
            ));
            playerCopCar.get(0).scaleObject(0.1f, 0.1f, 0.1f);
            playerCopCar.get(0).translateObject(0.0125f, 0.0f, 0.0f);



            playerCopCar.get(0).getChildObject().add(new Model( // body white
                    hem,
                    new ArrayList<>(),
//                new Vector4f(0.5f,0.f,0.f,1.0f),
                    new Vector4f(169/255f, 167/255f, 172/255f, 1.0f),
                    "resources/model/rexy/cop/body white.obj"
            ));
            playerCopCar.get(0).getChildObject().get(0).scaleObject(0.1f, 0.1f, 0.1f);
//            playerCopCar.get(0).getChildObject().get(0).translateObject(-0.0125f, 0.0f, 0.0f);


            playerCopCar.get(0).getChildObject().add(new Model( // rubber
                    hem,
                    new ArrayList<>(),
//                new Vector4f(0.5f,0.f,0.f,1.0f),
                    new Vector4f(16/255f, 15/255f, 14/255f, 1.0f),
                    "resources/model/rexy/cop/rubber.obj"
            ));
            playerCopCar.get(0).getChildObject().get(1).scaleObject(0.1f, 0.1f, 0.1f);

            playerCopCar.get(0).getChildObject().add(new Model( // velg
                    hem,
                    new ArrayList<>(),
//                new Vector4f(0.5f,0.f,0.f,1.0f),
                    new Vector4f(235/255f, 230/255f, 255/255f, 1.0f),
                    "resources/model/rexy/cop/velg.obj"
            ));
            playerCopCar.get(0).getChildObject().get(2).scaleObject(0.1f, 0.1f, 0.1f);


            playerCopCar.get(0).getChildObject().add(new Model( // blue
                    hem,
                    new ArrayList<>(),
//                new Vector4f(0.5f,0.f,0.f,1.0f),
                    new Vector4f(19/255f, 35/255f, 96/255f, 1.0f),
                    "resources/model/rexy/cop/blue.obj"
            ));
            playerCopCar.get(0).getChildObject().get(3).scaleObject(0.1f, 0.1f, 0.1f);

            playerCopCar.get(0).getChildObject().add(new Model( // red
                    hem,
                    new ArrayList<>(),
//                new Vector4f(0.5f,0.f,0.f,1.0f),
                    new Vector4f(97/255f, 30/255f, 36/255f, 1.0f),
                    "resources/model/rexy/cop/red.obj"
            ));
            playerCopCar.get(0).getChildObject().get(4).scaleObject(0.1f, 0.1f, 0.1f);

            playerCopCar.get(0).getChildObject().add(new Model( // lmpu blkg
                    hem,
                    new ArrayList<>(),
//                new Vector4f(0.5f,0.f,0.f,1.0f),
                    new Vector4f(85/255f, 25/255f, 31/255f, 1.0f),
                    "resources/model/rexy/cop/lampu blkg.obj"
            ));
            playerCopCar.get(0).getChildObject().get(5).scaleObject(0.1f, 0.1f, 0.1f);

            playerCopCar.get(0).getChildObject().add(new Model( // lmpu dpn
                    hem,
                    new ArrayList<>(),
//                new Vector4f(0.5f,0.f,0.f,1.0f),
                    new Vector4f(255/255f, 223/255f, 90/255f, 1.0f),
                    "resources/model/rexy/cop/lampu depan.obj"
            ));
            playerCopCar.get(0).getChildObject().get(6).scaleObject(0.1f, 0.1f, 0.1f);








            playerCopCar.get(0).translateObject(-0.2f, -0.06f, 0f);
            playerCopCar.get(0).rotateObject((float)Math.toRadians(90f),0f,1f,0f);

        } // cop car

        {
            // ground (objectGround(0))
            objectGround.add(new Model(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(79/255f, 79/255f, 79/255f, 1.0f),
                    "resources/model/rexy/Terrain_Grass_Flat_1x1.obj"
            ));
            // "resources/model/track/Terrain_Grass_Flat_1x1.obj"
            objectGround.get(0).scaleObject(20f, 1f, 20f);
            objectGround.get(0).translateObject(0f, -0.565f, 0f);
        } //ground


        camera.setRotation(0f,(float)Math.toRadians(90f));

        camera.setPosition(playerCopCar.get(0).getJarakX(),playerCopCar.get(0).getJarakY(),
                -playerCopCar.get(0).getJarakZ());
        camera.moveRight(0.26f);
        camera.moveUp(0.1f);
    }

    private void ngenggg(int arah) {
        //pindahin smua objek ke state 0
        for (int i = 0; i< playerCopCar.size(); i++) {
            playerCopCar.get(i).translateObject(-playerCopCar.get(i).getJarakX(), -playerCopCar.get(i).getJarakY(), playerCopCar.get(i).getJarakZ());
            playerCopCar.get(i).rotateObject((float)Math.toRadians(-playerCopCar.get(i).getRotation()), 0f,1f,0f);
            playerCopCar.get(i).rotateObject((float) Math.toRadians(10f), 0f, 0f, -1f);
        }
        //pindahin smua objek ke state awal sebelum ke state 0
        for (int i = 0; i< playerCopCar.size(); i++) {
            playerCopCar.get(i).rotateObject((float) Math.toRadians(10f), 0f, 0f, 1f);
            playerCopCar.get(i).rotateObject((float)Math.toRadians(playerCopCar.get(i).getRotation()), 0f,1f,0f);
            playerCopCar.get(i).translateObject(playerCopCar.get(i).getJarakX(), playerCopCar.get(i).getJarakY(), -playerCopCar.get(i).getJarakZ());
        }
    }

    public boolean collideTa(ArrayList<Object> ob, boolean maju, float rotate) {
        boolean collideTaa = false;
        for (int i = 0; i < ob.size(); i++) {
            ob.get(i).rotateObject((float) Math.toRadians(rotate * 2f), 0f, 1f, 0f);
            ob.get(i).rexTranslateObject(maju?0.02f:-0.02f, 0f, 0f);
            ob.get(i).rotateObject((float) Math.toRadians(rotate * -2f), 0f, 1f, 0f);
        }

        float x = ob.get(0).getJarakX();
        float y = ob.get(0).getJarakY();
        float z = ob.get(0).getJarakZ();
        if ( x>2.9 ){
            System.out.println("timur Nubruk bos!");
            collideTaa = true;
        }
        else if( x<-2.9 ){
            System.out.println("barat Nubruk bos!");
            collideTaa = true;
        }
        if ( z>2.9 ){
            System.out.println("utara Nubruk bos!");
            collideTaa = true;
        }
        else if( z<-3.6 ){
            System.out.println("selatan Nubruk bos!");
            collideTaa = true;
        }

        for (int i = 0; i < ob.size(); i++) {
            ob.get(i).rotateObject((float) Math.toRadians(rotate * 2f), 0f, 1f, 0f);
            ob.get(i).rexTranslateObject(maju?-0.02f:0.02f, 0f, 0f);
            ob.get(i).rotateObject((float) Math.toRadians(rotate * -2f), 0f, 1f, 0f);
        }
        System.out.println("colide? "+ collideTaa);
        return (collideTaa);
    }

    int respawn = 1;
    int playerbrp = 1;
    int cobaRex = 2;
    float thirdPersonBack = 3f;
    float thirdPersonUp = 1f;
    float firstperson = 1f;
    float rotateCop = 0;

    float thirdPerson = 0;
    float thirdPerson2 = 0;
    int hadep=0;
    float rotate1=0;
    float rotate2=0;
    int majuCollideTa=0;
    int mundurCollideTa=0;
    int majuColideTank=0;
    int mundurCollideTank=0;
    public void input() {
//        System.out.println("jarak lampu1: "+lights.get(0).getJarakX()+" , "+lights.get(0).getJarakY()+" , "+lights.get(0).getJarakZ());


        copPosition = playerCopCar.get(0).getCenterPoint();
        angle = angle % (float) Math.toRadians(360);

        if (playerbrp!=1){
            thirdPerson=0;
        }

        if (window.isKeyPressed(GLFW_KEY_0)){
            playerbrp=0;
        }

        if (window.isKeyPressed(GLFW_KEY_1)){
            playerbrp=1;

            camera.setRotation(0f,(float)Math.toRadians(90f));
            camera.addRotation(0f,(float)Math.toRadians(rotate1*2f));
            camera.setPosition(playerCopCar.get(0).getJarakX(),playerCopCar.get(0).getJarakY(),
                    -playerCopCar.get(0).getJarakZ());
            camera.moveRight(0.26f);
            camera.moveUp(0.1f);
        }

        if (window.isKeyPressed(GLFW_KEY_2)){
            playerbrp=2;

            camera.setRotation(0f,(float)Math.toRadians(180f));
            camera.addRotation(0f,(float)Math.toRadians(rotate2*2f));
            camera.setPosition(playerTank.get(0).getJarakX(),playerTank.get(0).getJarakY(),
                    -playerTank.get(0).getJarakZ());

            camera.moveUp(0.25f);
            camera.moveRight(0.2f);
            camera.moveBackwards(0.5f);
        }

        if(window.isKeyPressed(GLFW_KEY_T)){
            if (playerbrp == 1) {
                if (thirdPerson == 0) {
                    thirdPerson = 1;
                    System.out.println("THIRD PERSON");
                }

                camera.setPosition(playerCopCar.get(0).getJarakX(), playerCopCar.get(0).getJarakY(),
                        -playerCopCar.get(0).getJarakZ());
                System.out.println(playerCopCar.get(0).getJarakX() + " " + playerCopCar.get(0).getJarakY() + " "
                        + -playerCopCar.get(0).getJarakZ());
                camera.moveRight(0.26f);
                camera.moveUp(0.1f);
                if (thirdPerson == 1) {
                    camera.moveBackwards(3.2f);
                    camera.moveUp(1.5f);
                }
            }
            else if (playerbrp == 2){
                if (thirdPerson == 0) {
                    thirdPerson = 1;
                    System.out.println("THIRD PERSON");
                }

                camera.setPosition(playerTank.get(0).getJarakX(),playerTank.get(0).getJarakY(),
                        playerTank.get(0).getJarakZ());

                System.out.println(playerTank.get(0).getJarakX() + " " + playerTank.get(0).getJarakY() + " "
                        + -playerTank.get(0).getJarakZ());
                camera.moveUp(0.1f);

                if (thirdPerson == 1) {
                    camera.moveBackwards(3.2f);
                    camera.moveUp(1.0f);
                }
            }
        }


        if(window.isKeyPressed(GLFW_KEY_F)){
            if (playerbrp == 1) {
                if (thirdPerson == 1) {
                    thirdPerson = 0;
                    System.out.println("FIRST PERSON");
                }

                camera.setPosition(playerCopCar.get(0).getJarakX(), playerCopCar.get(0).getJarakY(),
                        -playerCopCar.get(0).getJarakZ());
                camera.moveRight(0.26f);
                camera.moveUp(0.1f);
            }
            else if (playerbrp == 2){
                camera.setPosition(playerTank.get(0).getJarakX(),playerTank.get(0).getJarakY(),
                        playerTank.get(0).getJarakZ());
                camera.moveUp(0.25f);
                camera.moveRight(0.2f);
                camera.moveBackwards(0.5f);
            }
        }

//        if(window.isKeyPressed(GLFW_KEY_T) && playerbrp==1){
//            if (thirdPerson==0){
//                thirdPerson=1;
//                System.out.println("THIRD PERSON");
//            }
//
//            camera.setPosition(playerCopCar.get(0).getJarakX(),playerCopCar.get(0).getJarakY(),
//                    -playerCopCar.get(0).getJarakZ());
//            camera.moveRight(0.26f);
//            camera.moveUp(0.1f);
//            if (thirdPerson==1){
//                camera.moveBackwards(3.2f);
//                camera.moveUp(1.5f);
//            }
//        }
//
//
//        if(window.isKeyPressed(GLFW_KEY_F) && playerbrp==1){
//
//            if (thirdPerson==1){
//                thirdPerson=0;
//                System.out.println("FIRST PERSON");
//            }
//
//
//            camera.setPosition(playerCopCar.get(0).getJarakX(),playerCopCar.get(0).getJarakY(),
//                    -playerCopCar.get(0).getJarakZ());
//            camera.moveRight(0.26f);
//            camera.moveUp(0.1f);
//        }


        if (window.isKeyPressed(GLFW_KEY_W) && playerbrp==1) {
            System.out.println(majuCollideTa);
            int collisionTa = 0;
            float x = playerCopCar.get(0).getJarakX();
            float y = playerCopCar.get(0).getJarakY();
            float z = playerCopCar.get(0).getJarakZ();

//            majuCollideTa=cekDepanNubruk(x,y,z);
//            mundurCollideTa=cekBelakangNubruk(x,y,z);
            if (collideTa(playerCopCar,true,rotate1)==true){
                majuCollideTa=1;
            }else{
                majuCollideTa=0;
            }
            if( majuCollideTa == 0) {
                System.out.println("MAJU");
                System.out.println("MAJU");
                System.out.println("MAJU");
//            ngenggg(0);
                for (int i = 0; i < playerCopCar.size(); i++) {
                    playerCopCar.get(i).rotateObject((float) Math.toRadians(rotate1 * 2f), 0f, 1f, 0f);
                    playerCopCar.get(i).rexTranslateObject(0.02f, 0f, 0f);
                    playerCopCar.get(i).rotateObject((float) Math.toRadians(rotate1 * -2f), 0f, 1f, 0f);
                }

                camera.setPosition(playerCopCar.get(0).getJarakX(),playerCopCar.get(0).getJarakY(),
                        -playerCopCar.get(0).getJarakZ());
                camera.moveForward(0.02f);
                camera.moveRight(0.26f);
                camera.moveUp(0.1f);

                if (thirdPerson==1){
                    camera.moveBackwards(3.2f);
                    camera.moveUp(1.5f);
                }
//                if (mundurCollideTa==1){
//                    mundurCollideTa=0;
//                }
            }

        }




        if (window.isKeyPressed(GLFW_KEY_S) && playerbrp==1) {
            System.out.println(mundurCollideTa);
            int collideTa = 0;
            float x = playerCopCar.get(0).getJarakX();
            float y = playerCopCar.get(0).getJarakY();
            float z = playerCopCar.get(0).getJarakZ();

//            majuCollideTa=cekDepanNubruk(x,y,z);
//            mundurCollideTa=cekBelakangNubruk(x,y,z);

            if (collideTa(playerCopCar,false,rotate1) == true){
                mundurCollideTa=1;
            }else {
                mundurCollideTa=0;
            }

            if (mundurCollideTa == 0){
                System.out.println("MUNDUR");
                System.out.println("MUNDUR");
                System.out.println("MUNDUR");
                for (int i = 0; i < playerCopCar.size(); i++) {
                    playerCopCar.get(i).rotateObject((float) Math.toRadians(rotate1 * 2f), 0f, 1f, 0f);
                    playerCopCar.get(i).rexTranslateObject(-0.02f, 0, 0);
                    playerCopCar.get(i).rotateObject((float) Math.toRadians(rotate1 * -2f), 0f, 1f, 0f);
                }


                camera.setPosition(playerCopCar.get(0).getJarakX(), playerCopCar.get(0).getJarakY(),
                        -playerCopCar.get(0).getJarakZ());
                camera.moveBackwards(0.02f);
                camera.moveRight(0.26f);
                camera.moveUp(0.1f);

                if (thirdPerson == 1) {
                    camera.moveBackwards(3.2f);
                    camera.moveUp(1.5f);
                }


            }

        }






        if (window.isKeyPressed(GLFW_KEY_A) && playerbrp==1) {
            for (int i = 0; i < playerCopCar.size(); i++) {
                playerCopCar.get(i).translateObject(-playerCopCar.get(i).getJarakX(), -playerCopCar.get(i).getJarakY(), playerCopCar.get(i).getJarakZ());
                playerCopCar.get(i).rexRotateObject((float) Math.toRadians(2f), 0f, 1f, 0f);//woi z
                // blm
                playerCopCar.get(i).translateObject(playerCopCar.get(i).getJarakX(), playerCopCar.get(i).getJarakY(), -playerCopCar.get(i).getJarakZ());
            }
            rotate1 -= 1;


            camera.setPosition(playerCopCar.get(0).getJarakX(), playerCopCar.get(0).getJarakY(),
                    -playerCopCar.get(0).getJarakZ());
            camera.addRotation(0f, (float) Math.toRadians(-2f));
            camera.moveRight(0.26f);
            camera.moveUp(0.1f);

            if (thirdPerson == 1) {
                camera.moveBackwards(3.2f);
                camera.moveUp(1.5f);
            }


            // cek collision
            System.out.println("X: " + playerCopCar.get(0).getJarakX());
            // X : 5.2
            System.out.println("Y: " + playerCopCar.get(0).getJarakY());
            // Z : 2.9
            System.out.println("Z: " + playerCopCar.get(0).getJarakZ());
        }


        if (window.isKeyPressed(GLFW_KEY_D) && playerbrp==1) {
            for (int i = 0; i < playerCopCar.size(); i++) {
                playerCopCar.get(i).translateObject(-playerCopCar.get(i).getJarakX(), -playerCopCar.get(i).getJarakY(), playerCopCar.get(i).getJarakZ());
                playerCopCar.get(i).rexRotateObject((float) Math.toRadians(-2f), 0, 1f, 0);
                playerCopCar.get(i).translateObject(playerCopCar.get(i).getJarakX(), playerCopCar.get(i).getJarakY(), -playerCopCar.get(i).getJarakZ());
            }
            rotate1 += 1;


            camera.setPosition(playerCopCar.get(0).getJarakX(),playerCopCar.get(0).getJarakY(),
                    -playerCopCar.get(0).getJarakZ());
            camera.addRotation(0f,(float) Math.toRadians(2f));
            camera.moveRight(0.26f);
            camera.moveUp(0.1f);

            if (thirdPerson==1){
                camera.moveBackwards(3.2f);
                camera.moveUp(1.5f);
            }


            // cek collision
            System.out.println("X: " + playerCopCar.get(0).getJarakX());
            // X : 5.2
            System.out.println("Y: " + playerCopCar.get(0).getJarakY());
            // Z : 2.9
            System.out.println("Z: " + playerCopCar.get(0).getJarakZ());
        }



        if(window.isKeyPressed(GLFW_KEY_W) && playerbrp==0){
            camera.moveForward(0.01f);
        }
        if(window.isKeyPressed(GLFW_KEY_S) && playerbrp==0){
            camera.moveBackwards(0.01f);
        }
        if(window.isKeyPressed(GLFW_KEY_A) && playerbrp==0){
            camera.moveLeft(0.01f);
        }
        if(window.isKeyPressed(GLFW_KEY_D) && playerbrp==0){
            camera.moveRight(0.01f);
        }
        if(window.isKeyPressed(GLFW_KEY_UP) && playerbrp==0){
//            camera.moveUp(0.01f);
            camera.addRotation((float)Math.toRadians(-0.3f),0);
        }
        if(window.isKeyPressed(GLFW_KEY_DOWN) && playerbrp==0){
//            camera.moveDown(0.01f);
            camera.addRotation((float)Math.toRadians(0.3f),0);
        }
        if(window.isKeyPressed(GLFW_KEY_RIGHT) && playerbrp==0){
            camera.addRotation(0,(float)Math.toRadians(0.7f));
        }
        if(window.isKeyPressed(GLFW_KEY_LEFT) && playerbrp==0){
            camera.addRotation(0,(float)Math.toRadians(-0.7f));
        }


        // gerakan tnak
        if (window.isKeyPressed(GLFW_KEY_P ) && playerbrp == 2){
//            // step - balik ke asal - rotate - tranlasi
//            // pakai merkurius
//            Vector3f merkurius = objectSphere.get(countPlanet).getModel().transformPosition(new Vector3f(0.0f, 0.0f, 0.0f));
//            objectSphere.get(countPlanet).translateObject(-merkurius.x, -merkurius.y , -merkurius.z);
//            objectSphere.get(countPlanet).rotateObject((float) Math.toRadians(1.9f), 0.0f, 0.0f, 0.5f);
//            objectSphere.get(countPlanet).translateObject(merkurius.x, merkurius.y , merkurius.z);
//            countPlanet++;
            // kepala putar
            Vector3f tankBody = playerTank.get(0).getModel().transformPosition(new Vector3f(0f,0f,0f));

            playerTank.get(0).translateObjectParent(-tankBody.x, -tankBody.y, -tankBody.z);
            playerTank.get(0).rotateObjectParent((float)Math.toRadians(2.0f), 0.0f, 1.0f, 0.0f);
            playerTank.get(0).translateObjectParent(tankBody.x, tankBody.y, tankBody.z);
        }


        if(window.isKeyPressed(GLFW_KEY_W) && playerbrp == 2) {
            System.out.println(majuColideTank);
            int collisionTa = 0;
            float x = playerTank.get(0).getJarakX();
            float y = playerTank.get(0).getJarakY();
            float z = playerTank.get(0).getJarakZ();

//            majuCollideTa=cekDepanNubruk(x,y,z);
//            mundurCollideTa=cekBelakangNubruk(x,y,z);

            if (majuColideTank == 0) {
                System.out.println("MAJU");
                System.out.println("MAJU");
                System.out.println("MAJU");
//            ngenggg(0);
                for (int i = 0; i < playerTank.size(); i++) {
                    playerTank.get(i).rotateObject((float) Math.toRadians(rotate2 * 2f), 0f, 1f, 0f);
                    playerTank.get(i).rexTranslateObject(0.00f, 0f, 0.02f);
                    playerTank.get(i).rotateObject((float) Math.toRadians(rotate2 * -2f), 0f, 1f, 0f);
                }

                camera.moveForward(0.02f);

                if (thirdPerson == 1) {
                    camera.moveBackwards(3.2f);
                    camera.moveUp(1.5f);
                }
//                if (mundurCollideTa==1){
//                    mundurCollideTa=0;
//                }
            }
        }

//        if(window.isKeyPressed(GLFW_KEY_A) && playerbrp == 2){
//            for (int i = 0; i < playerCopCar.size(); i++) {
//                playerTank.get(i).translateObject(-playerTank.get(i).getJarakX(), -playerTank.get(i).getJarakY(), playerTank.get(i).getJarakZ());
//                playerTank.get(i).rexRotateObject((float) Math.toRadians(2f), 0, 1f, 0);
//                playerTank.get(i).translateObject(playerTank.get(i).getJarakX(), playerTank.get(i).getJarakY(), -playerTank.get(i).getJarakZ());
//            }
//            rotate2 += 1;
//
//        }
        if(window.isKeyPressed(GLFW_KEY_S) && playerbrp == 2){
            System.out.println(majuColideTank);
            int collisionTa = 0;
            float x = playerTank.get(0).getJarakX();
            float y = playerTank.get(0).getJarakY();
            float z = playerTank.get(0).getJarakZ();

//            majuCollideTa=cekDepanNubruk(x,y,z);
//            mundurCollideTa=cekBelakangNubruk(x,y,z);

                mundurCollideTank = 0;

                System.out.println("MUNDUR");
                System.out.println("MUNDUR");
                System.out.println("MUNDUR");
//            ngenggg(0);
                for (int i = 0; i < playerTank.size(); i++) {
                    playerTank.get(i).rotateObject((float) Math.toRadians(rotate2 * 2f), 0f, 1f, 0f);
                    playerTank.get(i).rexTranslateObject(0.00f, 0f, -0.02f);
                    playerTank.get(i).rotateObject((float) Math.toRadians(rotate2 * -2f), 0f, 1f, 0f);
                }

                camera.moveBackwards(0.02f);

                if (thirdPerson == 1) {
                    camera.moveBackwards(3.2f);
                    camera.moveUp(1.5f);
                }
//                if (mundurCollideTa==1){
//                    mundurCollideTa=0;
//                }
            }
        }

//        if(window.isKeyPressed(GLFW_KEY_D) && playerbrp == 2){
//            for (int i = 0; i < playerCopCar.size(); i++) {
//                playerTank.get(i).translateObject(-playerTank.get(i).getJarakX(), -playerTank.get(i).getJarakY(), playerTank.get(i).getJarakZ());
//                playerTank.get(i).rexRotateObject((float) Math.toRadians(-2f), 0, 1f, 0);
//                playerTank.get(i).translateObject(playerTank.get(i).getJarakX(), playerTank.get(i).getJarakY(), -playerTank.get(i).getJarakZ());
//            }
//            rotate2 += 1;
//        }






//        if (window.isKeyPressed(GLFW_KEY_2)){
//            playerbrp=2;
//
//            camera.setRotation(0f,(float)Math.toRadians(90f));
//            camera.addRotation(0f,(float)Math.toRadians(rotate2*2f));
//            camera.setPosition(playerTank.get(0).getJarakX(),playerTank.get(0).getJarakY(),
//                    -playerTank.get(0).getJarakZ());
//        }
//
//        if (window.isKeyPressed(GLFW_KEY_W) && playerbrp==2) {
//            if (playerbrp == 2) {
//                System.out.println(majuCollideTa2);
//                float x = playerTank.get(0).getJarakX();
//                float y = playerTank.get(0).getJarakY();
//                float z = playerTank.get(0).getJarakZ();
//
//                if (collideTa(playerTank, true, rotate2) == true) {
//                    majuCollideTa2 = 1;
//                } else {
//                    majuCollideTa2 = 0;
//                }
//                if (majuCollideTa2 == 0) {
//                    System.out.println("MAJU");
//                    System.out.println("MAJU");
//                    System.out.println("MAJU");
////            ngenggg(0);
//                    for (int i = 0; i < playerTank.size(); i++) {
//                        playerTank.get(i).rotateObject((float) Math.toRadians(rotate2 * 2f), 0f,
//                                1f, 0f);
//                        playerTank.get(i).rexTranslateObject(0.02f, 0f, 0f);
//                        playerTank.get(i).rotateObject((float) Math.toRadians(rotate2 * -2f), 0f,
//                                1f, 0f);
//                    }
//
//                    camera.setPosition(playerTank.get(0).getJarakX(), playerTank.get(0).getJarakY(),
//                            -playerTank.get(0).getJarakZ());
//                    camera.moveForward(0.02f);
//                    camera.moveRight(0.26f);
//                    camera.moveUp(0.1f);
//
//                    if (thirdPerson2 == 1) {
//                        camera.moveBackwards(3.2f);
//                        camera.moveUp(1.5f);
//                    }
////                if (mundurCollideTa==1){
////                    mundurCollideTa=0;
////                }
//                }
//            }
//
//            else if (playerbrp == 2) {
//                System.out.println(majuCollideTa2);
//                int collisionTa = 0;
//                float x = playerTank.get(0).getJarakX();
//                float y = playerTank.get(0).getJarakY();
//                float z = playerTank.get(0).getJarakZ();
//
//                if (collideTa(playerTank, true, rotate2) == true) {
//                    majuCollideTa2 = 1;
//                } else {
//                    majuCollideTa2 = 0;
//                }
//                if (majuCollideTa2 == 0) {
//                    System.out.println("MAJU");
//                    System.out.println("MAJU");
//                    System.out.println("MAJU");
////            ngenggg(0);
//                    for (int i = 0; i < playerTank.size(); i++) {
//                        playerTank.get(i).rotateObject((float) Math.toRadians(rotate2 * 2f), 0f, 1f, 0f);
//                        playerTank.get(i).rexTranslateObject(0.00f, 0f, 0.02f);
//                        playerTank.get(i).rotateObject((float) Math.toRadians(rotate2 * -2f), 0f, 1f, 0f);
//                    }
//
//                    camera.moveForward(0.02f);
//
//                    if (thirdPerson2 == 1) {
//                        camera.moveBackwards(3.2f);
//                        camera.moveUp(1.5f);
//                    }
////                if (mundurCollideTa==1){
////                    mundurCollideTa=0;
////                }
//                }
//            }
//
//        }
//
//        if (window.isKeyPressed(GLFW_KEY_S)  && playerbrp==2) {
//            if (playerbrp == 2) {
//                System.out.println(mundurCollideTa2);
//                int collideTa = 0;
//                float x = playerTank.get(0).getJarakX();
//                float y = playerTank.get(0).getJarakY();
//                float z = playerTank.get(0).getJarakZ();
//
////            majuCollideTa=cekDepanNubruk(x,y,z);
////            mundurCollideTa=cekBelakangNubruk(x,y,z);
//
//                if (collideTa(playerTank, false, rotate2) == false) {
//                    mundurCollideTa2 = 1;
//                } else {
//                    mundurCollideTa2 = 0;
//                }
//
//                if (mundurCollideTa2 == 0) {
//                    System.out.println("MUNDUR");
//                    System.out.println("MUNDUR");
//                    System.out.println("MUNDUR");
//                    for (int i = 0; i < playerTank.size(); i++) {
//                        playerTank.get(i).rotateObject((float) Math.toRadians(rotate2 * 2f), 0f,
//                                1f, 0f);
//                        playerTank.get(i).rexTranslateObject(-0.02f, 0, 0);
//                        playerTank.get(i).rotateObject((float) Math.toRadians(rotate2 * -2f), 0f,
//                                1f, 0f);
//                    }
//
//
//                    camera.setPosition(playerTank.get(0).getJarakX(), playerTank.get(0).getJarakY(),
//                            -playerTank.get(0).getJarakZ());
//                    camera.moveBackwards(0.02f);
//                    camera.moveRight(0.26f);
//                    camera.moveUp(0.1f);
//
//                    if (thirdPerson2 == 1) {
//                        camera.moveBackwards(3.2f);
//                        camera.moveUp(1.5f);
//                    }
//
//
//                }
//            }
//            else if (playerbrp == 2){
//                {
//                    System.out.println(majuCollideTa2);
//                    int collisionTa = 0;
//                    float x = playerTank.get(0).getJarakX();
//                    float y = playerTank.get(0).getJarakY();
//                    float z = playerTank.get(0).getJarakZ();
//
////            majuCollideTa=cekDepanNubruk(x,y,z);
////            mundurCollideTa=cekBelakangNubruk(x,y,z);
//                    if (collideTa(playerTank, true, rotate2) == true) {
//                        majuCollideTa2 = 1;
//                    } else {
//                        majuCollideTa2 = 0;
//                    }
//                    if (majuCollideTa2 == 0) {
//                        System.out.println("MAJU");
//                        System.out.println("MAJU");
//                        System.out.println("MAJU");
////            ngenggg(0);
//                        for (int i = 0; i < playerTank.size(); i++) {
//                            playerTank.get(i).rotateObject((float) Math.toRadians(rotate2 * 2f), 0f, 1f, 0f);
//                            playerTank.get(i).rexTranslateObject(0.00f, 0f, -0.02f);
//                            playerTank.get(i).rotateObject((float) Math.toRadians(rotate2 * -2f), 0f, 1f, 0f);
//                        }
//
//                        camera.moveBackwards(0.02f);
//
//                        if (thirdPerson2 == 1) {
//                            camera.moveBackwards(3.2f);
//                            camera.moveUp(1.5f);
//                        }
////                if (mundurCollideTa==1){
////                    mundurCollideTa=0;
////                }
//                    }
//                }
//            }
//        }



























    public void loop() {
        while (window.isOpen()) {
            window.update();

            glClearColor(0.0f, 0.64453125f, 1.0f, 1.0f);

            GL.createCapabilities();

            input();

            // code here

            for (Object object: objectGround) {
                object.draw(camera, projection);
            }


//            for (Object object: playerBotak) {
//                object.draw(camera, projection);
//            }
            for (Object object: lightsRex) {
                object.draw(camera, projection);
            }

            for (Object object: cityPoly) {
                object.draw(camera, projection);
            }
            for (Object object: playerCopCar) {
                object.draw(camera, projection);
            }


//            for (Object object: rexyObject) {
//                object.draw(camera, projection);
//            }
//            for (Object object: lights) {
//                object.draw(camera, projection);
//            }
            for (Object object: playerTank) {
                object.draw(camera, projection);
            }

            for (Object object: orangAneh) {
                object.draw(camera, projection);
            }

            // Restore state
            glDisableVertexAttribArray(0);
            // Poll for window events.
            // The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().run();
    }
}
