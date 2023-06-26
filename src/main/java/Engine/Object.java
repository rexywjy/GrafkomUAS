package Engine;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class Object extends ShaderProgram{


    List<Vector3f> normal = new ArrayList<>();

    List<Vector3f> vertices;
    int vao;
    int vbo;
    UniformsMap uniformsMap;
    Vector4f color;
    public Matrix4f model;
    List<Object> childObject;
    List<Float> centerPoint;
    boolean scene = true;


    float xR;
    float yR;
    float zR;
    float r;


    public void setScene(boolean scene) {
        this.scene = scene;
    }

    public List<Object> getChildObject() {
        return childObject;
    }

    public List<Float> getCenterPoint() {
        updateCenterPoint();
        return centerPoint;
    }

    public Object(List<ShaderModuleData> shaderModuleDataList
            , List<Vector3f> vertices
            , Vector4f color) {
        super(shaderModuleDataList);
        this.vertices = vertices;
//        setupVAOVBO();
        uniformsMap = new UniformsMap(getProgramId());
        uniformsMap.createUniform(
                "uni_color");
        uniformsMap.createUniform(
                "model");
        uniformsMap.createUniform(
                "projection");
        uniformsMap.createUniform(
                "view");
        uniformsMap.createUniform("dirLight.direction");
        uniformsMap.createUniform("dirLight.ambient");
        uniformsMap.createUniform("dirLight.diffuse");
        uniformsMap.createUniform("dirLight.specular");
        for(int i = 0; i < 10; i++){
            uniformsMap.createUniform("pointLight["+i+"].position");
            uniformsMap.createUniform("pointLight["+i+"].ambient");
            uniformsMap.createUniform("pointLight["+i+"].diffuse");
            uniformsMap.createUniform("pointLight["+i+"].specular");
            uniformsMap.createUniform("pointLight["+i+"].constant");
            uniformsMap.createUniform("pointLight["+i+"].linear");
            uniformsMap.createUniform("pointLight["+i+"].quadratic");
        }
        uniformsMap.createUniform("spotLight.position");
        uniformsMap.createUniform("spotLight.direction");
        uniformsMap.createUniform("spotLight.ambient");
        uniformsMap.createUniform("spotLight.diffuse");
        uniformsMap.createUniform("spotLight.specular");
        uniformsMap.createUniform("spotLight.constant");
        uniformsMap.createUniform("spotLight.linear");
        uniformsMap.createUniform("spotLight.quadratic");
        uniformsMap.createUniform("spotLight.cutOff");
        uniformsMap.createUniform("spotLight.outerCutOff");
        uniformsMap.createUniform("viewPos");
        this.color = color;
        model = new Matrix4f().identity();
        childObject = new ArrayList<>();
        centerPoint = Arrays.asList(0f,0f,0f);
    }

    public void setupVAOVBO(){
        //set vao
        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        //set vbo
        vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER,
                Utils.listoFloat(vertices),
                GL_STATIC_DRAW);
    }

    public void drawSetup(Camera camera, Projection projection){
        bind();
        uniformsMap.setUniform(
                "uni_color", color);
        uniformsMap.setUniform(
                "model", model);
        uniformsMap.setUniform(
                "view", camera.getViewMatrix());
        uniformsMap.setUniform(
                "projection", projection.getProjMatrix());
        uniformsMap.setUniform("dirLight.direction", new Vector3f(-0.2f, -1.0f, -0.3f));
        if(scene){
            uniformsMap.setUniform("dirLight.ambient", new Vector3f(0.1f, 0.1f, 0.1f));
        } else {
            uniformsMap.setUniform("dirLight.ambient", new Vector3f(0.8f, 0.8f, 0.8f));
        }
        uniformsMap.setUniform("dirLight.diffuse", new Vector3f(0.4f, 0.4f, 0.4f));
        uniformsMap.setUniform("dirLight.specular", new Vector3f(0.5f, 0.5f, 0.5f));

        Vector3f[] _pointLightPositions = {
                new Vector3f(-0.8f, 0.7f, 0.1f), // lampu 1
                new Vector3f(-0.8f, 0.7f, 3.3f), // lampu 2
                new Vector3f(-0.8f, 0.7f, 4.9f), // lampu 3
                new Vector3f(-2.8f, 0.7f, -1.1f), // lampu 4
                new Vector3f(4.4f, 0.7f, 2.05f), // lampu 5
                new Vector3f(-4.6f, 0.7f, -2.4f), // lampu 6
                new Vector3f(-4.6f, 0.7f, 4.9f), // lampu 7
                new Vector3f(2.4f, 0.7f, -4.2f), // lampu 8
                new Vector3f(-0.8f, 0.7f, -4.7f), // lampu 9
                new Vector3f(2f, 1f, 4.3f), // lampu 10 ANGIE
        };

        for(int i = 0; i < _pointLightPositions.length; i++){
            uniformsMap.setUniform("pointLight["+i+"].position", _pointLightPositions[i]);
            if(scene){
                uniformsMap.setUniform("pointLight["+i+"].ambient", new Vector3f(0.3f, 0.3f, 0.3f));
            } else {
                uniformsMap.setUniform("pointLight["+i+"].ambient", new Vector3f(0.3f, 0.3f, 0.3f));
            }
            uniformsMap.setUniform("pointLight["+i+"].diffuse", new Vector3f(0.8f, 0.8f, 0.8f));
            uniformsMap.setUniform("pointLight["+i+"].specular", new Vector3f(0.5f, 0.5f, 0.5f));
            uniformsMap.setUniform("pointLight["+i+"].constant", 1.0f);
            uniformsMap.setUniform("pointLight["+i+"].linear", 0.22f);
            uniformsMap.setUniform("pointLight["+i+"].quadratic", 0.20f);
        }

        // spotLight
        uniformsMap.setUniform("spotLight.position", camera.getPosition());
        uniformsMap.setUniform("spotLight.direction", new Vector3f(
                camera.getViewMatrix().get(2,0),
                camera.getViewMatrix().get(2,1),
                -camera.getViewMatrix().get(2,2)
        ));
        uniformsMap.setUniform("spotLight.ambient",new Vector3f(.05f, .05f, .05f));
        uniformsMap.setUniform("spotLight.diffuse",new Vector3f(1.0f,1.0f,1.0f));
        uniformsMap.setUniform("spotLight.specular",new Vector3f(.75f, .75f, .75f));
        uniformsMap.setUniform("spotLight.constant",1.0f);
        uniformsMap.setUniform("spotLight.linear",0.09f);
        uniformsMap.setUniform("spotLight.quadratic",0.032f);
        uniformsMap.setUniform("spotLight.cutOff",(float)Math.cos(Math.toRadians(12f)));
        uniformsMap.setUniform("spotLight.outerCutOff",(float)Math.cos(Math.toRadians(12.5f)));

        uniformsMap.setUniform("viewPos", camera.getPosition());

        // Bind VBO
        glEnableVertexAttribArray(0);
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glVertexAttribPointer(0, 3,
                GL_FLOAT,
                false,
                0, 0);

    }

    public void draw(Camera camera, Projection projection){
        drawSetup(camera, projection);
        // Draw the vertices
        //optional
        glLineWidth(10); //ketebalan garis
        glPointSize(10); //besar kecil vertex
        //wajib
        //GL_LINES
        //GL_LINE_STRIP
        //GL_LINE_LOOP
        //GL_TRIANGLES
        //GL_TRIANGLE_FAN
        //GL_POINT
        glDrawArrays(GL_TRIANGLES,
                0,
                vertices.size());
        for(Object child:childObject){
            child.draw(camera,projection);
        }
    }

    public void translateObject(Float offsetX,Float offsetY,Float offsetZ){
        model = new Matrix4f().translate(offsetX,offsetY,offsetZ).mul(new Matrix4f(model));
        // update center point tak apus buat rotasi di tempat
        for(Object child:childObject){
            child.translateObject(offsetX,offsetY,offsetZ);
        }
    }

    public void translateObjectParent(Float offsetX,Float offsetY,Float offsetZ){
        model = new Matrix4f().translate(offsetX,offsetY,offsetZ).mul(new Matrix4f(model));
    }

    public void rotateObject(Float degree, Float x,Float y,Float z){
        model = new Matrix4f().rotate(degree,x,y,z).mul(new Matrix4f(model));
        // update center point tak apus buat rotasi di tempat
        for(Object child:childObject){
            child.rotateObject(degree,x,y,z);
        }
    }

    public void rotateObjectParent(Float degree, Float x,Float y,Float z){
        model = new Matrix4f().rotate(degree,x,y,z).mul(new Matrix4f(model));
    }
    public void updateCenterPoint(){
        Vector3f destTemp = new Vector3f();
        model.transformPosition(0.0f,0.0f,0.0f,destTemp);
        centerPoint.set(0,destTemp.x);
        centerPoint.set(1,destTemp.y);
        centerPoint.set(2,destTemp.z);
    }
    public void scaleObject(Float scaleX,Float scaleY,Float scaleZ){
        model = new Matrix4f().scale(scaleX,scaleY,scaleZ).mul(new Matrix4f(model));
        for(Object child:childObject){
            child.translateObject(scaleX,scaleY,scaleZ);
        }
    }

    public void scaleObject(float scale) {
        model = new Matrix4f().scale(scale, scale, scale).mul(new Matrix4f(model));
        for(Object child:childObject) {
            child.scaleObject(scale, scale, scale);
        }
    }

    public void setxR(float xR) {
        this.xR = xR;
    }

    public void setyR(float yR) {
        this.yR = yR;
    }



    public float getxR() {
        return 1;
    }

    public float getyR() {
        return 1;
    }
    public float getzR() {
        return zR;
    }

    public void setzR(float zR) {
        this.zR = zR;
    }


    public float getJarakX() {
        return 1;
    }

    public float getJarakY() {
        return 1;
    }

    public float getJarakZ() {
        return 1;
    }

    public float getRotation() {
        return 1;
    }

    public void rexTranslateObject(float v, float v1, float v2) {
    }
    public void rexRotateObject(float toRadians, float v, float v1, float v2) {
    }



    public Object(List<ShaderModuleData> shaderModuleDataList
            , List<Vector3f> vertices
            , Vector4f color, ArrayList<Float> centerPoint) {
        super(shaderModuleDataList);


        this.vertices = vertices;
//        setupVAOVBO();
        uniformsMap = new UniformsMap(getProgramId());
        uniformsMap.createUniform(
                "uni_color");
        uniformsMap.createUniform(
                "model");
        uniformsMap.createUniform(
                "projection");
        uniformsMap.createUniform(
                "view");
        uniformsMap.createUniform("dirLight.direction");
        uniformsMap.createUniform("dirLight.ambient");
        uniformsMap.createUniform("dirLight.diffuse");
        uniformsMap.createUniform("dirLight.specular");
        for(int i = 0; i < 10; i++){
            uniformsMap.createUniform("pointLight["+i+"].position");
            uniformsMap.createUniform("pointLight["+i+"].ambient");
            uniformsMap.createUniform("pointLight["+i+"].diffuse");
            uniformsMap.createUniform("pointLight["+i+"].specular");
            uniformsMap.createUniform("pointLight["+i+"].constant");
            uniformsMap.createUniform("pointLight["+i+"].linear");
            uniformsMap.createUniform("pointLight["+i+"].quadratic");
        }
        uniformsMap.createUniform("spotLight.position");
        uniformsMap.createUniform("spotLight.direction");
        uniformsMap.createUniform("spotLight.ambient");
        uniformsMap.createUniform("spotLight.diffuse");
        uniformsMap.createUniform("spotLight.specular");
        uniformsMap.createUniform("spotLight.constant");
        uniformsMap.createUniform("spotLight.linear");
        uniformsMap.createUniform("spotLight.quadratic");
        uniformsMap.createUniform("spotLight.cutOff");
        uniformsMap.createUniform("spotLight.outerCutOff");
        uniformsMap.createUniform("viewPos");
        this.color = color;
        model = new Matrix4f().identity();
        childObject = new ArrayList<>();


        this.centerPoint = centerPoint;
    }

    public Matrix4f getModel() {
        return model;
    }
}

