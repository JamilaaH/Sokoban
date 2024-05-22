package sokoban.view;

import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import sokoban.model.GameElement;
import sokoban.model.Goal;
import sokoban.viewmodel.CellViewModel;

import java.util.List;

/**
 * Classe abstraite représentant une vue de cellule dans le jeu Sokoban.
 * Cette classe gère l'affichage des éléments de jeu dans une cellule.
 */
public abstract class CellView extends StackPane {

    // Images des différents éléments de jeu
    protected static final Image box = new Image("box.png");
    protected static final Image ground = new Image("ground.png");
    protected static final Image goal = new Image("goal.png");

    protected final CellViewModel viewModel;
    protected final DoubleBinding widthProperty;
    protected final DoubleBinding heightProperty;

    // Panneau principal pour empiler les éléments de la cellule
    protected StackPane stackPane = new StackPane();
    // Composants visuels pour afficher les images des éléments
    protected ImageView imageView = new ImageView();
    protected ImageView midImageView = new ImageView();
    protected ImageView topImageView = new ImageView();

    /**
     * Constructeur pour CellView.
     *
     * @param viewModel le modèle de vue associé à cette cellule
     * @param cellWidthProperty la propriété de largeur de la cellule
     * @param cellHeightProperty la propriété de hauteur de la cellule
     */
    public CellView(CellViewModel viewModel, DoubleBinding cellWidthProperty, DoubleBinding cellHeightProperty) {
        this.viewModel = viewModel;
        this.widthProperty = cellWidthProperty;
        this.heightProperty = cellHeightProperty;
        setAlignment(Pos.CENTER);
        init();
    }

    /**
     * Gère l'affichage du numéro de la boîte s'il y a une boîte dans la cellule.
     */
    protected void handleBoxNumber() {
        Label numberLabel = viewModel.createBoxNumberLabel();
        if (numberLabel != null && stackPane.getChildren().isEmpty()) {
            stackPane.getChildren().add(numberLabel);
            StackPane.setAlignment(numberLabel, Pos.CENTER);
        }
    }

    /**
     * Initialise les images des éléments dans la cellule.
     */
    protected void init() {
        List<GameElement> elements = viewModel.getCellValue().get();

        // Définit l'image de base pour tous les types de cellules
        imageView.setImage(ground);

        // Gère l'affichage des images selon les éléments présents dans la cellule
        if (elements.size() > 2 && elements.get(1) instanceof Goal) {
            midImageView.setImage(elements.get(2).getImage());
            topImageView.setImage(elements.get(1).getImage());
            handleBoxNumber();
            System.out.println("init yu");
        } else {
            if (elements.size() > 1) {
                midImageView.setImage(elements.get(1).getImage());
            }
            if (elements.size() > 2) {
                topImageView.setImage(elements.get(2).getImage());
            }
            if (viewModel.isBox()) {
                handleBoxNumber();
                System.out.println("init ya");
            }
        }
    }

    protected void setImage(List<GameElement> elements) {
        midImageView.setImage(null);
        topImageView.setImage(null);
        stackPane.getChildren().clear();


        if (elements.size() > 2 && elements.get(1) instanceof Goal) {
            midImageView.setImage(elements.get(2).getImage());
            topImageView.setImage(elements.get(1).getImage());
            handleBoxNumber();
            System.out.println("set yu");
        } else {
            if (elements.size() > 1) {
                midImageView.setImage(elements.get(1).getImage());
            }
            if (elements.size() > 2) {
                topImageView.setImage(elements.get(2).getImage());
            }
            if (viewModel.isBox()) {
                handleBoxNumber();
                System.out.println("set ya");
            }
        }
    }
}
