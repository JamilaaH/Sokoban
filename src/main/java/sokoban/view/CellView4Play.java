package sokoban.view;

import javafx.beans.binding.DoubleBinding;
import sokoban.viewmodel.CellViewModel;

import java.util.List;

public class CellView4Play extends CellView{
    CellView4Play(CellViewModel viewModel, DoubleBinding cellWidthProperty, DoubleBinding cellHeightProperty) {
        super(viewModel, cellWidthProperty, cellHeightProperty);
        configureBindings();
    }

    private void configureBindings() {
        // Configuration des liaisons de taille pour les images
        imageView.fitWidthProperty().bind(widthProperty);
        imageView.fitHeightProperty().bind(heightProperty);
        midImageView.fitWidthProperty().bind(widthProperty);
        midImageView.fitHeightProperty().bind(heightProperty);
        topImageView.fitWidthProperty().bind(widthProperty);
        topImageView.fitHeightProperty().bind(heightProperty);

        // Ajout des composants visuels au StackPane
        getChildren().addAll(imageView, midImageView,stackPane, topImageView);

        // Écoutez les changements dans les cellules
        viewModel.valueProperty().addListener((obs, old, newVal) -> setImage(newVal));
    }
}
