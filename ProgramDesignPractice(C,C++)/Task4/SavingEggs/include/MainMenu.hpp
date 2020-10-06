#pragma once
#include "SFML/Window.hpp"
#include "SFML/Graphics.hpp"
#include <list>

class MainMenu
{

public:
	enum MenuResult { Nothing, Exit, Play, Help, Settings };

	struct MenuItem
	{
	public:
		sf::Rect<int> rect;
		MenuResult action;
	};	
	MenuResult Show(sf::RenderWindow&);

private:
	MenuResult getMenuResponse(sf::RenderWindow&);
	MenuResult handleClick(int, int);
	
	std::list<MenuItem> _menuItems;
};

