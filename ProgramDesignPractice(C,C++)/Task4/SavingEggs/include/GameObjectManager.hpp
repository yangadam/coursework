#pragma once
#include "stdafx.hpp"
#include "VisibleGameObject.hpp"

class GameObjectManager
{
public:	
	GameObjectManager();
	~GameObjectManager();

	void add(std::string name, VisibleGameObject* gameObject);
	void remove(std::string name);
	int getObjectCount() const;
	VisibleGameObject* get(std::string name) const;

	virtual void drawAll(sf::RenderWindow& renderWindow);
	virtual void updateAll(float);

private:
	std::map<std::string, VisibleGameObject*> _gameObjects;

	struct GameObjectDeallocator
	{
		void operator()(const std::pair<std::string,VisibleGameObject*> & p) const
		{
			delete p.second;
		}
	};
};