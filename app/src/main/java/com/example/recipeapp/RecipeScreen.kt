package com.example.recipeapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun RecipeScreen(modifier: Modifier = Modifier,
                 viewState: MainViewModel.RecipeState,
                 navigateToDetail: (Category)-> Unit){

    val recipeViewModel: MainViewModel = viewModel()

    Box(modifier = Modifier.fillMaxSize()){
        when{
            viewState.loading -> {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
            viewState.error != null ->{
                Text("Error Occurred")
            }

            else ->{
                //Display Categories
                CategoryScreen(categories = viewState.list, navigateToDetail)
            }
        }
    }

}


@Composable
fun CategoryScreen(categories: List<Category>, navigateToDetail: (Category)-> Unit){
    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.fillMaxSize()){

        items(categories){
            category ->
            CategoryItem(category = category, navigateToDetail)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryItem(category: Category,
                 navigateToDetail: (Category)-> Unit
                 ){
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
            .clickable {
                       navigateToDetail(category)
            },
        horizontalAlignment = Alignment.CenterHorizontally
        ) {

        Image(
            painter = rememberAsyncImagePainter(category.strCategoryThumb),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
        )


        Text(
            text = category.strCategory,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 4.dp)
        )

    }


}
