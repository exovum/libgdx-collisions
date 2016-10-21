package com.exovum.test.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * Created by exovu_000 on 10/18/2016.
 * This is the menu screen for the Kordan Jirby running animation game.
 * It serves as the start screen when the application is opened,
 * and provides access to Credits, Instructions, and the Game itself.
 */

public class AnimatorMenuScreen implements Screen {

    private SpriteBatch batch;

    private Stage stage;
    private Table table;
    private Skin skin;
    Actor root;
    ShapeRenderer renderer;

    public AnimatorMenuScreen(SpriteBatch batch) {
        this.batch = batch;

        /* FIRST TEST
        // Sample setup for a Stage with a Table UI
        stage = new Stage(new FitViewport(800, 480));
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        table.setDebug(true); // This is optional, but enables debug lines for tables.

        // Add widgets to the table
        */

        /*
         SECOND TEST - Buttons
        */
        stage = new Stage(new StretchViewport(800,480));
        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

        Label nameLabel = new Label("Name:", skin);
        TextField nameText = new TextField("", skin);
        Label addressLabel = new Label("Address:", skin);
        TextField addressText = new TextField("", skin);

        Table table = new Table();
        //stage.addActor(table);
        table.setSize(260, 195);
        table.setPosition(190, 142);
        table.align(Align.right | Align.bottom);

        table.debug();

        TextureRegion upRegion = skin.getRegion("default-slider-knob");
        TextureRegion downRegion = skin.getRegion("default-slider-knob");
        BitmapFont buttonFont = skin.getFont("default-font");

        TextButton button = new TextButton("Button 1", skin);
        button.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("touchDown 1");
                return false;
            }
        });
        table.add(button);
        // table.setTouchable(Touchable.disabled);


        Table table2 = new Table();
        //stage.addActor(table2);
        table2.setFillParent(true);
        //table2.setScale(2.0f);
        //table2.center();
        //table2.top();

        TextButton button2 = new TextButton("Button 2", skin);
        button2.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("2!");
            }
        });
        button2.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("touchDown 2");
                return false;
            }
        });
        table2.add(button2);
        //button2.top();

        TextButton creditsButton = new TextButton("Credits", skin);
        creditsButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("View Credits");
            }
        });
        //table2.add(creditsButton);
        //creditsButton.bottom();

        // Use a VerticalGroup instead of a Table -> stacks items verticall (obv)
        // and is more lightweight than a Table
        VerticalGroup menuVerticalGroup= new VerticalGroup();
        stage.addActor(menuVerticalGroup);
        menuVerticalGroup.setFillParent(true);

        menuVerticalGroup.center();

        menuVerticalGroup.addActor(button2);
        menuVerticalGroup.addActor(creditsButton);
        menuVerticalGroup.columnCenter();

        /*
            THIRD TEST - Text/Labels
         */
        /*renderer = new ShapeRenderer();
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        skin.getAtlas().getTextures().iterator().next().setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        skin.getFont("default-font").getData().markupEnabled = true;
        float scale = 1;
        skin.getFont("default-font").getData().setScale(scale);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        stage.addActor(table);
        table.setPosition(200, 65);

        table.debug();
        table.add(new Label("This is regular text.", skin));
        table.row();
        table.add(new Label("This is regular text\nwith a newline.", skin));
        table.row();
        Label label3 = new Label("This is [RED]regular text\n\nwith newlines,\nalignedbottom right.", skin);
        label3.setColor(Color.GREEN);
        label3.setAlignment(Align.bottom | Align.right);
        table.add(label3).minWidth(200 * scale).minHeight(110 * scale).fill();
        table.row();
        Label label4 = new Label("This is regular text with NO newlines, wrap enabled and aligned bottom, right.", skin);
        label4.setWrap(true);
        label4.setAlignment(Align.bottom | Align.right);
        table.add(label4).minWidth(200 * scale).minHeight(110 * scale).fill();
        table.row();
        Label label5 = new Label("This is regular text with\n\nnewlines, wrap\nenabled and aligned bottom, right.", skin);
        label5.setWrap(true);
        label5.setAlignment(Align.bottom | Align.right);
        table.add(label5).minWidth(200 * scale).minHeight(110 * scale).fill();

        table.pack();*/
    }



    @Override
    public void show() {

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
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

        /*float x = 40, y = 40;

        BitmapFont font = skin.getFont("default-font");
        batch.begin();
        font.draw(batch, "The quick brown fox jumped over the lazy cow.", x, y);
        batch.end();

        drawLine(x, y - font.getDescent(), x + 1000, y - font.getDescent());
        drawLine(x, y - font.getCapHeight() + font.getDescent(), x + 1000, y - font.getCapHeight() + font.getDescent());
        */
    }

    public void drawLine(float x1, float y1, float x2, float y2) {
        renderer.setProjectionMatrix(batch.getProjectionMatrix());
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.line(x1, y1, x2, y2);
        renderer.end();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }

}
