#ifdef GL_ES
    precision mediump float;
#endif

uniform vec2 u_mouseCoords;
varying vec4 v_color;
varying vec2 v_texCoords;

uniform sampler2D u_texture;
uniform int u_height;
uniform mat4 u_projTrans;

void main() {
    //lets try and make one that is slowly more gray scale farther from the mouse
    vec4 color = texture2D(u_texture, v_texCoords).rgba;

    vec2 pixelCoords = gl_FragCoord.xy;
    //pixelCoords.y = pixelCoords.y + u_height;

    float distanceBetween = distance(u_mouseCoords, pixelCoords);
//    if(distanceBetween < 300){
//        gl_FragColor = vec4(color);
//    }else if(distanceBetween == 302){
//        gl_FragColor = vec4(0, 0, 0, color.a);
//    }else
    if (distanceBetween < 500){
        float gradient = (distanceBetween - 0) / 500;
        vec4 grayScale = vec4(vec3((color.r + color.g + color.b) / 600.0), color.a);
        gl_FragColor = mix(color, grayScale, gradient);
    }else if(distanceBetween == 502){
        gl_FragColor = vec4(0, 0, 0, color.a);
    }else{
        float gray = (color.r + color.g + color.b) / 3.0;
        gl_FragColor = vec4(vec3(gray), color.a);
    }







}