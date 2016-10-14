package com.exovum.test.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by exovu_000 on 10/14/2016.
 */

public class AnimatorMainScreen implements Screen {

    static final int WORLD_WIDTH = 100;
    static final int WORLD_HEIGHT = 100;

    private Viewport viewport;
    private OrthographicCamera camera;
    SpriteBatch batch;
    private TextureAtlas atlas;

    Animation jkirbyAnimation;
    AnimatedSprite jkirbyAnimatedSprite;

    // The two Sprites for the map are used to "loop" the map as the player runs
    private Sprite mapSprite, mapSprite2;
    // Boolean to determine which map is on the main screen; used to know when to switch map positions
    boolean displayMap1;

    float floorPos;

    public AnimatorMainScreen(SpriteBatch batch) {
        this.batch = batch;



        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        // Set the floor to be 100 below the middle of the screen
        floorPos = -150;

        // Constructs a new OrthographicCamera, using the given viewport width and height
        // Height is multiplied by aspect ratio.
        camera = new OrthographicCamera(30, 30 * (h / w));
        viewport = new FitViewport(800, 480, camera);
        camera.update();
        viewport.update(800, 480);
        //camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        //camera.update();

        // Start the "displayFirstMap" flag as true, because map1 is shown first
        displayMap1 = true;
        mapSprite = new Sprite(new Texture(Gdx.files.internal
                ("beach-ocean-sea-bg/transparent-png/full_background.png")));
        mapSprite2 = new Sprite(new Texture(Gdx.files.internal
                ("beach-ocean-sea-bg/transparent-png/full_background.png")));
        //viewport.get
        // Position the map to the bottom-left of the screen
        mapSprite.setPosition(-400, -220);
        // Resize the map so the height fits within the view, but keep original width
        mapSprite.setSize(mapSprite.getRegionWidth(), viewport.getScreenHeight());
        // Repeat adjustments for map2
        mapSprite2.setPosition(mapSprite.getX() + mapSprite.getWidth(), mapSprite.getY());
        mapSprite2.setSize(mapSprite.getRegionWidth(), viewport.getScreenHeight());


        // Setup the TextureAtlas for the jkirby running frames
        atlas = new TextureAtlas(Gdx.files.internal("jkirby_atlas.atlas"));
        // Find all frames of jkirby running from the TextureAtlas, and store in an Array
        Array<TextureAtlas.AtlasRegion> jkirbyFrames = atlas.findRegions("jk-frame");

        // Create the animation using the frames from the TextureAtlas
        jkirbyAnimation = new Animation(0.025f, jkirbyFrames);
        jkirbyAnimation.setPlayMode(Animation.PlayMode.LOOP);
        // Create an AnimatedSprite, which contains the Animation and Sprite information
        jkirbyAnimatedSprite = new AnimatedSprite(jkirbyAnimation);
        jkirbyAnimatedSprite.play();
        //jkirbyAnimatedSprite.setPosition(-250, floorPos);
        jkirbyAnimatedSprite.setPosition(-1 * viewport.getScreenWidth() / 2, floorPos);
        //jkirbyAnimatedSprite.setSize(2,3);
    }

    @Override
    public void render(float delta) {
        handleInput();
        // auto-move the background left, so the animation to the left
        camera.translate(
                3, 0, 0);
        jkirbyAnimatedSprite.setPosition(jkirbyAnimatedSprite.getX() +3, jkirbyAnimatedSprite.getY());
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Begin SpriteBatch rendering
        batch.begin();
        mapSprite.draw(batch);
        mapSprite2.draw(batch);
        if(displayMap1) {
            // check if switching from mapSprite to mapSprite2
            // plus (sprite.width * 3) so map1 isn't visible when it disappears
            if(jkirbyAnimatedSprite.getX() >= mapSprite2.getX() +
                    jkirbyAnimatedSprite.getWidth() *3) {
                //Once the player has switched to map2, move map1 to after map2
                mapSprite.setPosition(mapSprite2.getX() + mapSprite2.getWidth(),
                        mapSprite2.getY());
                // switch the "displayFirstMap" off
                displayMap1 = false;
            }
        } else {
            // check if switching from map2 to map1
            // plus (sprite.width * 3) so map1 isn't visible when it disappears
            if(jkirbyAnimatedSprite.getX() >= mapSprite.getX() +
                    jkirbyAnimatedSprite.getWidth() *3) {
                //Once the player has switched to map2, move map1 to after map2
                mapSprite2.setPosition(mapSprite.getX() + mapSprite.getWidth(),
                        mapSprite.getY());
                // switch the "displayFirstMap" on
                displayMap1 = true;
            }
        }
        jkirbyAnimatedSprite.draw(batch);
        // End SpriteBatch rendering
        batch.end();
    }

    private void handleInput() {
        //TODO: handle moving left/right, scrolling background, moving animation
        //TODO: handle touch/gesture events [touchDown to jump higher]


    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {
        //camera.viewportWidth = width / 32f;
        //camera.viewportHeight = camera.viewportWidth * height/width;
        viewport.update(width, height);
        camera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        mapSprite.getTexture().dispose();
        jkirbyAnimatedSprite.getTexture().dispose();
        batch.dispose();
    }
}
