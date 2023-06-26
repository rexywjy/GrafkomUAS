#version 400 core

struct SpotLight{
    vec3 position;
    vec3 direction;
    float cutOff;
    float outerCutOff;

    vec3 ambient;
    vec3 diffuse;
    vec3 specular;

    float constant;
    float linear;
    float quadratic;
};

in vec2 pass_textureCoordinates;
in vec3 surfaceNormal;
in vec3 toLightVector[20];
in vec3 toCameraVector;
in float visibility;
in vec3 fragPos;

out vec4 out_Color;

uniform sampler2D modelTexture;
uniform vec3 lightColour[20];
uniform vec3 attenuation[20];
uniform float shineDamper;
uniform float reflectivity;
uniform vec3 skyColour;

uniform SpotLight spotLight;
uniform vec3 viewPos;

vec3 CalcSpotLight(SpotLight light, vec3 normal, vec3 fragPos, vec3 viewDir)
{

    //diffuse shading
    vec3 lightDir = normalize(light.position - fragPos);
    float diff = max(dot(normal, lightDir), 0.0);

    //specular shading
    vec3 reflectDir = reflect(-lightDir, normal);
    float spec = pow(max(dot(viewDir, reflectDir), 0.0),3072);

    //attenuation
    float distance    = length(light.position - fragPos);
    float attenuation = 1.0 / (light.constant + light.linear * distance +
    light.quadratic * (distance * distance));

    //spotlight intensity
    float theta     = dot(lightDir, normalize(-light.direction));
    float epsilon   = light.cutOff - light.outerCutOff;
    float intensity = clamp((theta - light.outerCutOff) / epsilon, 0.0, 1.0);

    //combine results
    vec3 ambient = light.ambient;
    vec3 diffuse = light.diffuse * diff;
    vec3 specular = light.specular * spec;
    ambient  *= attenuation;
    diffuse  *= attenuation * intensity;
    specular *= attenuation * intensity;
    return (ambient + diffuse + specular);
}

void main(void){
    vec3 unitNormal = normalize(surfaceNormal);
    vec3 unitVectorToCamera = normalize(toCameraVector);

    vec3 totalDiffuse =vec3(0.0);
    vec3 totalSpecular =vec3(0.0);

    for(int i=0;i<20;i++){
        float distance = length(toLightVector[i]);
        float attFactor = attenuation[i].x + (attenuation[i].y*distance) + (attenuation[i].z * distance * distance);
        vec3 unitLightVector = normalize(toLightVector[i]);
        float nDotl = dot(unitNormal,unitLightVector);
        float brightness = max(nDotl,0.0);
        vec3 lightDirection = -unitLightVector;
        vec3 reflectedLightDirection = reflect(lightDirection,unitNormal);
        float specularFactor = dot(reflectedLightDirection , unitVectorToCamera);
        specularFactor = max(specularFactor,0.0);
        float dampedFactor = pow(specularFactor,shineDamper);
        totalDiffuse = totalDiffuse + (brightness * lightColour[i])/attFactor;
        totalSpecular = totalSpecular + (dampedFactor * reflectivity * lightColour[i])/attFactor;
    }
    totalDiffuse = max(totalDiffuse,0.2);

    vec4 textureColour = texture(modelTexture,pass_textureCoordinates);
    if(textureColour.a<0.5){
        discard;
    }
    vec3 viewDir = normalize(viewPos - fragPos);
    vec3 result  = CalcSpotLight(spotLight,unitNormal,fragPos,viewDir);

    out_Color =  vec4(totalDiffuse,1.0) * textureColour+ vec4(totalSpecular,1.0) +vec4(result,1.0);
    out_Color = mix(vec4(skyColour,1.0),out_Color,visibility);
}