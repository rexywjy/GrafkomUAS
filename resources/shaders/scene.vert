#version 330
layout (location = 0) in vec3 position;
layout (location = 1) in vec3 normal;

uniform mat4 model;
uniform mat4 projection;
uniform mat4 view;
out vec3 Normal;
out vec3 FragPos;

void main() {
    gl_Position = projection * view * model * vec4(position, 1.0);
    Normal = normalize(model * vec4(position, 1.0)).xyz;
    FragPos = vec3(model * vec4(position, 1.0));
}

//#version 330
//layout (location = 0) in vec3 position;
//uniform mat4 model;
//uniform mat4 projection;
//uniform mat4 view;
//void main() {
//    gl_Position = projection * view * model * vec4(position, 1.0);
//    //    gl_Position = vec4(position, 1.0);
//}
