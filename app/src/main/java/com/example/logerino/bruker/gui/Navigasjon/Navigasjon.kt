package GUI.Navigasjon

import GUI.theme.BottomNavItem
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit,
    bottomBarState: MutableState<Boolean>)
{

    AnimatedVisibility(
        visible = bottomBarState.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
        content = {
            val backStackEntry = navController.currentBackStackEntryAsState()
            NavigationBar(
                modifier = modifier,
                containerColor = Color.Blue,
                tonalElevation = 5.dp,
            ) {
                items.forEach { item ->
                    val selected = item.route == backStackEntry.value?.destination?.route
                    NavigationBarItem(
                        selected = selected,
                        onClick = { onItemClick(item) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.Blue,
                            unselectedIconColor = Color.Gray
                        ),
                        icon = {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                if (item.badgeCount > 0) {
                                    BadgedBox(badge = { Badge { Text(item.badgeCount.toString()) } }
                                    ) {
                                        Icon(
                                            imageVector = item.icon,
                                            contentDescription = item.name
                                        )
                                    }

                                } else {
                                    Icon(
                                        imageVector = item.icon,
                                        contentDescription = item.name
                                    )
                                }
                                if (selected) {
                                    Text(
                                        text = item.name,
                                        textAlign = TextAlign.Center,
                                        fontSize = 10.sp
                                    )

                                }
                            }
                        }
                    )
                }
            }
        }
    )
}

/////////Brukt denne Youtube videoen under som mal for navigasjonsbaren
/* ======== KILDE: https://www.youtube.com/watch?v=4xyRnIntwTo ====== /
   ======== og det er blitt gjort en del endring fra orginal youtube video */