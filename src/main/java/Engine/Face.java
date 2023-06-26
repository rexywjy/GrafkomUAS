package Engine;

import org.joml.Vector2f;
import org.joml.Vector3f;

public class Face {
    Vector3f vertex = new Vector3f();
    Vector2f texture = new Vector2f();
    Vector3f normal = new Vector3f();

    public Face(Vector3f vertex, Vector3f normal) {
        this.vertex = vertex;
        this.normal = normal;
    }
    public Face(Vector3f vertex, Vector3f normal, Vector2f texture) {
        this.vertex = vertex;
        this.normal = normal;
        this.texture = texture;
    }
}
