#ifdef GL_ES
    precision mediump float;
#endif



uniform vec2 u_mouseCoords;
varying vec4 v_color;
varying vec2 v_texCoords;

uniform vec2 u_worldOffset;
uniform sampler2D u_texture;
uniform int u_height;
uniform mat4 u_projTrans;

struct Light {
    vec2 position;
    vec3 color;
    float intensity;
    float range;
    float angleStart;
    float angleEnd;
    int isActive;
};
#define MAX_LIGHTS 10
uniform int numLights;
uniform Light lights[MAX_LIGHTS];


void main() {
    //lets try and make one that is slowly more gray scale farther from the mouse
    vec4 color = texture2D(u_texture, v_texCoords).rgba;
    vec2 pixelCoords = (u_worldOffset.xy + gl_FragCoord.xy);

    float gray = (color.r + color.g + color.b) / 100.0;
    vec4 newColor = vec4(vec3(gray), color.a);

    for (int i = 0; i < numLights; ++i) {
        if(lights[i].isActive == 0) continue;
        float distanceBetween = distance(pixelCoords, lights[i].position);
        if(distanceBetween < lights[i].range){
            //okay now we only want to light up the pixel if its between 2 angle lines

            vec2 direction = pixelCoords - lights[i].position;
            float angle = atan(direction.y, direction.x);

            float angleDegrees = degrees(angle);
            if (angleDegrees < 0.0) {
                angleDegrees += 360.0; // Normalize negative angles
            }

            bool angleInRange = false;
            if (lights[i].angleStart < lights[i].angleEnd) {
                angleInRange = angleDegrees >= lights[i].angleStart && angleDegrees <= lights[i].angleEnd;
            } else {
                // The range crosses the 0/2π boundary
                angleInRange = angleDegrees >= lights[i].angleStart || angleDegrees <= lights[i].angleEnd;
            }

            if(angleInRange){
                float gradient = ((distanceBetween - 0.0) / lights[i].range);
                newColor = mix(color, newColor, gradient);
                //newColor = color;
            }


        }
    }


    //pixelCoords.y = pixelCoords.y + u_height;

    // Output the redscale color
    gl_FragColor = newColor;
}

