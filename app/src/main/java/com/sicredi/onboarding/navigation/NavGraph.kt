package com.sicredi.onboarding.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sicredi.onboarding.data.repository.AssociateRepository
import com.sicredi.onboarding.ui.screens.AssociateFormScreen
import com.sicredi.onboarding.ui.screens.AssociateListScreen
import com.sicredi.onboarding.ui.screens.LoginScreen
import com.sicredi.onboarding.viewmodel.AssociateViewModel
import com.sicredi.onboarding.viewmodel.AssociateViewModelFactory

sealed class Route(val route: String) {
    data object Login : Route("login")
    data object List : Route("list")

    // Deixa o parâmetro 'associateId' opcional como query param
    // Ex.: "form" (novo) ou "form?associateId=123" (editar)
    data object Form : Route("form?associateId={associateId}") {
        fun create(associateId: Long? = null): String =
            if (associateId == null) "form" else "form?associateId=$associateId"
    }
}

@Composable
fun SicrediNavGraph(repository: AssociateRepository) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.Login.route
    ) {
        composable(Route.Login.route) {
            LoginScreen(
                onLogged = {
                    navController.navigate(Route.List.route) {
                        popUpTo(Route.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Route.List.route) {
            val vm: AssociateViewModel =
                viewModel(factory = AssociateViewModelFactory(repository))

            AssociateListScreen(
                viewModel = vm,
                onAdd = { navController.navigate(Route.Form.create()) },
                onEdit = { id -> navController.navigate(Route.Form.create(id)) }
            )
        }

        // 'associateId' opcional: usamos defaultValue = -1L (sem nullable)
        composable(
            route = Route.Form.route,
            arguments = listOf(
                navArgument("associateId") {
                    type = NavType.LongType
                    defaultValue = -1L
                }
            )
        ) { backStackEntry ->
            val vm: AssociateViewModel =
                viewModel(factory = AssociateViewModelFactory(repository))

            val raw = backStackEntry.arguments?.getLong("associateId") ?: -1L
            val id: Long? = if (raw == -1L) null else raw

            AssociateFormScreen(
                viewModel = vm,
                associateId = id
            ) { navController.popBackStack() }
        }
    }
}
