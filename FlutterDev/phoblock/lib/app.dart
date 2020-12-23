import 'package:flutter/material.dart';
import 'Screens/EditProfileScreen/edit_profile.dart';
import 'Screens/HomeScreen/home_screen.dart';
import 'Screens/LoginScreen/login_signup_page.dart';
import 'Screens/Navigation/navigation_bar.dart';
import 'Screens/SignupScreen/signup_screen.dart';
import 'Screens/VoteServices/vote_services.dart';
import 'style.dart';

const AfterLoginRoute = '/Home';
const SignInRoute = '/';
const SignUpRoute = '/SignUp';
const VoteServicesRoute = '/VoteAndServices';

class App extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      //home: SignupScreen(),
      onGenerateRoute: _routes(),
      //home: LoginSignupPage(),
      theme: ThemeData(
        appBarTheme: AppBarTheme(
          textTheme: TextTheme(headline6: AppBarTextStyle),
        ),
        textTheme: TextTheme(
          bodyText1: SubtitleTextStyle,
        ),
      ),
    );
  }

  RouteFactory _routes() {
    return (settings) {
      final Map<String, dynamic> arguments = settings.arguments;
      Widget screen;

      switch (settings.name) {
        case SignInRoute:
          screen = LoginSignupPage();
          break;
        case SignUpRoute:
          screen = SignupScreen();
          break;
        case VoteServicesRoute:
          screen = VoteServices();
          break;
        case AfterLoginRoute:
          screen = NavigationBar(
            usernameLoggedIn: arguments['loginUsrname'],
          );
          break;
        default:
          return null;
      }
      return MaterialPageRoute(builder: (BuildContext context) => screen);
    };
  }
}
