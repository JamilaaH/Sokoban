package sokoban.view;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import sokoban.model.Cell;
import sokoban.model.CellValue;
import sokoban.viewmodel.CellViewModel;

public class CellView extends StackPane {
    private static final Image player = new Image("player.png");
    private static final Image box = new Image("box.png");
    private static final Image ground = new Image("ground.png");
    private static final Image wall = new Image("wall.png");
    private static final Image goal = new Image("goal.png");
    private final CellViewModel viewModel;
    private final DoubleBinding widthProperty;
    private final DoubleBinding heightProperty;

    private ImageView imageView = new ImageView();
    private ImageView topImageView = new ImageView();
    CellView(CellViewModel viewModel, DoubleBinding cellWidthProperty, DoubleBinding cellHeightProperty) {
        this.viewModel = viewModel;
        this.widthProperty = cellWidthProperty;
        this.heightProperty = cellHeightProperty;
        setAlignment(Pos.CENTER);

        configureBindings();

    }

    private void configureBindings() {

        minWidthProperty().bind(widthProperty);
        minHeightProperty().bind(heightProperty);
        //image de base en fond
        imageView.setImage(ground);
        imageView.setPreserveRatio(true);
        topImageView.setPreserveRatio(true);
        topImageView.fitHeightProperty().bind(heightProperty);
        topImageView.fitWidthProperty().bind(widthProperty);


        getChildren().addAll(imageView, topImageView);

        // un clic sur la cellule permet de jouer celle-ci
        this.setOnMouseClicked(e -> viewModel.play());

        // quand la cellule change de valeur, adapter l'image affichée
        viewModel.valueProperty().addListener((obs, old, newVal) -> setImage(imageView, newVal));

        //image grisé au moment du hover
        hoverProperty().addListener(this::hoverChanged);

    }

    //changement d'image au click
    private void setImage(ImageView imageView, CellValue cellValue) {
        if (cellValue == CellValue.WALL){
            topImageView.setImage(null);
            imageView.setImage(wall);
        } else if (cellValue == CellValue.BOX) {
            topImageView.setImage(null);
            imageView.setImage(box);
        } else if (cellValue == CellValue.GOAL) {
            topImageView.setImage(goal);
            imageView.setImage(ground);
        } else if (cellValue == CellValue.PLAYER) {
            topImageView.setImage(player);
            imageView.setImage(ground);
        } else {
            topImageView.setImage(null);
            imageView.setImage(ground);
        }


    }

    //image grisé au moment du hover
    private void hoverChanged(ObservableValue<? extends Boolean> obs, Boolean oldVal, Boolean newVal) {
        if (newVal) {
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setBrightness(-0.1); // Réduit la luminosité pour griser l'image
            imageView.setEffect(colorAdjust);
        } else {
            // Remettre l'image normale lorsque le survol est terminé
            imageView.setEffect(null);
        }
    }

    public void refresh() {
        //image de base en fond
        imageView.setImage(ground);
        imageView.setPreserveRatio(true);

        configureBindings();
    }
}
