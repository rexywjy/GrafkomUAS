package Engine;

import org.joml.*;
import org.lwjgl.opengl.GL11;

import java.io.*;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.glNormal3f;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;


public class Model extends Object{
    List <Vector3f> normals;
    List <Vector2f> textures;
    List <Face> faces;
    int nbo;



    float xR;
    float yR;
    float zR;
    float rotation;
    float jarakX, jarakY, jarakZ;
    Vector3f temp_vector = new Vector3f();

    public Model(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color, String filename) throws IOException {
        super(shaderModuleDataList, vertices, color);
        normals = new ArrayList<>();
        faces= new ArrayList<>();
        textures = new ArrayList<>();
        LoadModel(new File(filename));
        setupVAOVBO();
    }

    public void LoadModel(File f ) throws IOException {
        List <Vector3f> normalV= new ArrayList<>();
        List <Vector3f> verticeV= new ArrayList<>();
        List <Vector2f> textureV= new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(f));
        float x,y,z;
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("v ")) {
                    x = Float.parseFloat(line.split(" ")[1]);
                    y = Float.parseFloat(line.split(" ")[2]);
                    z = Float.parseFloat(line.split(" ")[3]);
                    System.out.println("Vertices: "+x+" "+y+" "+z);
                    verticeV.add(new Vector3f(x, y, z));
                }
                else if (line.startsWith("vn ")) {
                    x = Float.parseFloat(line.split(" ")[1]);
                    y = Float.parseFloat(line.split(" ")[2]);
                    z = Float.parseFloat(line.split(" ")[3]);
                    System.out.println("Normals: "+x+" "+y+" "+z);
                    normalV.add(new Vector3f(x, y, z));
                }
                else if (line.startsWith("vt ")) {
                    x = Float.parseFloat(line.split(" ")[1]);
                    y = Float.parseFloat(line.split(" ")[2]);

                    textureV.add(new Vector2f(x, y));
                }

                else if (line.startsWith("f ")) {
                    Vector3f vertexIndices = new Vector3f(
                            Float.parseFloat(line.split(" ")[1].split("/")[0]),
                            Float.parseFloat(line.split(" ")[2].split("/")[0]),
                            Float.parseFloat(line.split(" ")[3].split("/")[0]));

                    Vector3f normalIndices = new Vector3f(
                            Float.parseFloat(line.split(" ")[1].split("/")[2]),
                            Float.parseFloat(line.split(" ")[2].split("/")[2]),
                            Float.parseFloat(line.split(" ")[3].split("/")[2]));
                    if(textureV.size() > 0){
                        Vector3f TextureIndices = new Vector3f(Float.parseFloat(line.split(" ")[1].split("/")[1]),
                                Float.parseFloat(line.split(" ")[2].split("/")[1]),
                                Float.parseFloat(line.split(" ")[3].split("/")[1]));
                        faces.add(new Face(vertexIndices, normalIndices));
                    }
                    faces.add(new Face(vertexIndices, normalIndices));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        reader.close();

        for (Face face: faces ){
            Vector3f n1 = normalV.get((int)face.normal.x - 1);
            System.out.println(n1);
            normals.add(n1);
            Vector3f v1 = verticeV.get((int)face.vertex.x - 1);
            vertices.add(v1);
            Vector3f n2 = normalV.get((int)face.normal.y - 1);
            normals.add(n2);
            Vector3f v2 = verticeV.get((int)face.vertex.y - 1);
            vertices.add(v2);
            Vector3f n3 = normalV.get((int)face.normal.z - 1);
            normals.add(n3);
            Vector3f v3 = verticeV.get((int)face.vertex.z - 1);
            vertices.add(v3);
        }
    }

    public void setupVAOVBO(){
        //set vao
        super.setupVAOVBO();
        nbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, nbo);
        //mengirim vertices ke shader
        glBufferData(GL_ARRAY_BUFFER,
                Utils.listoFloat(normals),
                GL_STATIC_DRAW);
    }

    public void drawSetup(Camera camera, Projection projection) {
        super.drawSetup(camera, projection);
        glEnableVertexAttribArray(1);
        glBindBuffer(GL_ARRAY_BUFFER, nbo);
        glVertexAttribPointer(1,
                3, GL_FLOAT,
                false,
                0, 0);
    }





    public void rexTranslateObject(float offsetX, float offsetY, float offsetZ) {
        super.model = new Matrix4f().translate(offsetX, offsetY, offsetZ).mul(new Matrix4f(model));
        this.xR+=offsetX;
        this.yR+=offsetY;
        this.zR+=offsetZ;
        for(Object child:childObject) {
            child.translateObject(offsetX, offsetY, offsetZ);
        }
        //System.out.println(offsetX+" "+offsetY+" "+offsetZ);
        //System.out.println(rotation+"AWHJHWFA");
        jarakX+=offsetX*Math.cos(Math.toRadians(rotation));
        jarakY+=offsetY;
        jarakZ+=offsetX*Math.sin(Math.toRadians(rotation));
        //System.out.println("JarakX = "+jarakX+", JarakY = "+ jarakY+", JarakZ = "+jarakZ+", Rotation = "+rotation);
    }


    public void rexRotateObject(float degree, float x, float y, float z) {
        model = new Matrix4f().rotate(degree, x, y, z).mul(new Matrix4f(model));
        for(Object child:childObject) {
            child.rotateObject(degree, x, y, z);
        }
        this.xR = (float) (r * Math.cos(degree) + xR);
        this.yR = (float) (r * Math.sin(degree) + yR);
        rotation+=Math.toDegrees(degree);
        rotation=(360+rotation)%360f;
        //System.out.println(rotation+"DARI ROTATEOBJECT "+Math.toDegrees(degree));
    }

    public void scaleObject(float scaleX, float scaleY, float scaleZ) {
        model = new Matrix4f().scale(scaleX, scaleY, scaleZ).mul(new Matrix4f(model));
        for(Object child:childObject) {
            child.scaleObject(scaleX, scaleY, scaleZ);
        }
    }

    public void scaleObject(float scale) {
        model = new Matrix4f().scale(scale, scale, scale).mul(new Matrix4f(model));
        for(Object child:childObject) {
            child.scaleObject(scale, scale, scale);
        }
    }

    public Model(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color, ArrayList<Float> centerPoint) throws IOException {
        super(shaderModuleDataList, vertices, color, centerPoint);
        normals = new ArrayList<>();
        faces= new ArrayList<>();
        textures = new ArrayList<>();
        createBox(1.5f, 1.5f, 1.5f);
        setupVAOVBO();
    }

    public void createBox(float radiusX, float radiusY, float radiusZ){
        System.out.println(centerPoint);
        vertices.clear();
        Vector3f temp = new Vector3f();
        ArrayList<Vector3f> tempVertices = new ArrayList<>();
        //Titik 1 kiri atas belakang
        temp.x = centerPoint.get(0) - radiusX / 2;
        temp.y = centerPoint.get(1) + radiusY / 2;
        temp.z = centerPoint.get(2) - radiusZ / 2;
        tempVertices.add(temp);
        temp = new Vector3f();
        //Titik 2 kiri bawah belakang
        temp.x = centerPoint.get(0) - radiusX / 2;
        temp.y = centerPoint.get(1) - radiusY / 2;
        temp.z = centerPoint.get(2) - radiusZ / 2;
        tempVertices.add(temp);
        temp = new Vector3f();
        //Titik 3 kanan bawah belakang
        temp.x = centerPoint.get(0) + radiusX / 2;
        temp.y = centerPoint.get(1) - radiusY / 2;
        temp.z = centerPoint.get(2) - radiusZ / 2;
        tempVertices.add(temp);
        temp = new Vector3f();
        //Titik 4 kanan atas belakang
        temp.x = centerPoint.get(0) + radiusX / 2;
        temp.y = centerPoint.get(1) + radiusY / 2;
        temp.z = centerPoint.get(2) - radiusZ / 2;
        tempVertices.add(temp);
        temp = new Vector3f();
        //Titik 5 kiri atas depan
        temp.x = centerPoint.get(0) - radiusX / 2;
        temp.y = centerPoint.get(1) + radiusY / 2;
        temp.z = centerPoint.get(2) + radiusZ / 2;
        tempVertices.add(temp);
        temp = new Vector3f();
        //Titik 6 kiri bawah depan
        temp.x = centerPoint.get(0) - radiusX / 2;
        temp.y = centerPoint.get(1) - radiusY / 2;
        temp.z = centerPoint.get(2) + radiusZ / 2;
        tempVertices.add(temp);
        temp = new Vector3f();
        //Titik 7 kanan bawah depan
        temp.x = centerPoint.get(0) + radiusX / 2;
        temp.y = centerPoint.get(1) - radiusY / 2;
        temp.z = centerPoint.get(2) + radiusZ / 2;
        tempVertices.add(temp);
        temp = new Vector3f();
        //Titik 8 kanan atas depan
        temp.x = centerPoint.get(0) + radiusX / 2;
        temp.y = centerPoint.get(1) + radiusY / 2;
        temp.z = centerPoint.get(2) + radiusZ / 2;
        tempVertices.add(temp);
        temp = new Vector3f();

        //kotak belakang
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(2));

        vertices.add(tempVertices.get(2));
        vertices.add(tempVertices.get(3));
        vertices.add(tempVertices.get(0));
        //kotak depan
        vertices.add(tempVertices.get(4));
        vertices.add(tempVertices.get(5));
        vertices.add(tempVertices.get(6));

        vertices.add(tempVertices.get(6));
        vertices.add(tempVertices.get(7));
        vertices.add(tempVertices.get(4));
        //kotak samping kiri
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(4));

        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(5));
        vertices.add(tempVertices.get(4));
        //kotak samping kanan
        vertices.add(tempVertices.get(7));
        vertices.add(tempVertices.get(6));
        vertices.add(tempVertices.get(2));

        vertices.add(tempVertices.get(2));
        vertices.add(tempVertices.get(3));
        vertices.add(tempVertices.get(7));
        //kotak bawah
        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(5));
        vertices.add(tempVertices.get(6));

        vertices.add(tempVertices.get(6));
        vertices.add(tempVertices.get(2));
        vertices.add(tempVertices.get(1));
        //kotak atas
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(4));
        vertices.add(tempVertices.get(7));

        vertices.add(tempVertices.get(7));
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(3));

        normal = new ArrayList<>(Arrays.asList(
                //belakang
                new Vector3f(0.0f,0.0f,-1.0f),
                new Vector3f(0.0f,0.0f,-1.0f),
                new Vector3f(0.0f,0.0f,-1.0f),
                new Vector3f(0.0f,0.0f,-1.0f),
                new Vector3f(0.0f,0.0f,-1.0f),
                new Vector3f(0.0f,0.0f,-1.0f),
                //depan
                new Vector3f(0.0f,0.0f,1.0f),
                new Vector3f(0.0f,0.0f,1.0f),
                new Vector3f(0.0f,0.0f,1.0f),
                new Vector3f(0.0f,0.0f,1.0f),
                new Vector3f(0.0f,0.0f,1.0f),
                new Vector3f(0.0f,0.0f,1.0f),
                //kiri
                new Vector3f(-1.0f,0.0f,0.0f),
                new Vector3f(-1.0f,0.0f,0.0f),
                new Vector3f(-1.0f,0.0f,0.0f),
                new Vector3f(-1.0f,0.0f,0.0f),
                new Vector3f(-1.0f,0.0f,0.0f),
                new Vector3f(-1.0f,0.0f,0.0f),
                //kanan
                new Vector3f(1.0f,0.0f,0.0f),
                new Vector3f(1.0f,0.0f,0.0f),
                new Vector3f(1.0f,0.0f,0.0f),
                new Vector3f(1.0f,0.0f,0.0f),
                new Vector3f(1.0f,0.0f,0.0f),
                new Vector3f(1.0f,0.0f,0.0f),
                //bawah
                new Vector3f(0.0f,-1.0f,0.0f),
                new Vector3f(0.0f,-1.0f,0.0f),
                new Vector3f(0.0f,-1.0f,0.0f),
                new Vector3f(0.0f,-1.0f,0.0f),
                new Vector3f(0.0f,-1.0f,0.0f),
                new Vector3f(0.0f,-1.0f,0.0f),
                //atas
                new Vector3f(0.0f,1.0f,0.0f),
                new Vector3f(0.0f,1.0f,0.0f),
                new Vector3f(0.0f,1.0f,0.0f),
                new Vector3f(0.0f,1.0f,0.0f),
                new Vector3f(0.0f,1.0f,0.0f),
                new Vector3f(0.0f,1.0f,0.0f)
        ));
    }
    //
    public void addAllPositionCubeFaces(ArrayList<Vector3f> arrayLampuPosition, float radiusX, float radiusY, float radiusZ){
        arrayLampuPosition.add(new Vector3f(centerPoint.get(0), centerPoint.get(1), centerPoint.get(2) + radiusZ) );
        arrayLampuPosition.add(new Vector3f(centerPoint.get(0), centerPoint.get(1), centerPoint.get(2) - radiusZ) );
        arrayLampuPosition.add(new Vector3f(centerPoint.get(0), centerPoint.get(1) + radiusY ,  centerPoint.get(2)) );
        arrayLampuPosition.add(new Vector3f(centerPoint.get(0), centerPoint.get(1) - radiusY ,  centerPoint.get(2)) );
        arrayLampuPosition.add(new Vector3f(centerPoint.get(0) + radiusX, centerPoint.get(1)  ,  centerPoint.get(2)) );
        arrayLampuPosition.add(new Vector3f(centerPoint.get(0) - radiusX, centerPoint.get(1) ,  centerPoint.get(2)) );
    }




    @Override
    public float getxR() {
        return super.getxR();
    }

    @Override
    public float getyR() {
        return super.getyR();
    }

    @Override
    public float getzR() {
        return super.getzR();
    }
    public float getJarakX() {
        return jarakX;
    }

    public float getJarakY() {
        return jarakY;
    }

    public float getJarakZ() {
        return jarakZ;
    }


}
