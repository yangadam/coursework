#pragma once
#include "VisibleGameObject.h"

//Egg::_v =
class Egg:
	public VisibleGameObject
{
public:
	Egg();
	~Egg();
	void draw(sf::RenderWindow&);
	void update(float);
	bool isDropping();
	void setState(bool);
	static void setVelocity(float);
	float getVelocity();

private:
	enum EggState { Stop, Drop};

	EggState _eggState;
	static float _velocity;
	static unsigned _counter;
};