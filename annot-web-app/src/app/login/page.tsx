'use client'

import Link from "next/link";
import Image from "next/image";

export default function LoginPage() {

    return (
        <div className="flex justify-center items-center h-screen bg-purple-200">
            <div className="w-96 bg-white p-8 rounded-lg shadow-lg">
                <h1 className="text-lg font-semibold mb-6">Войдите в свой аккаунт</h1>

                <button
                    className="flex items-center justify-around bg-blue-500 hover:bg-blue-600 text-white font-medium py-2 px-4 rounded mb-3 w-full">
                    <Image src='/logos/google.svg' alt='' width='25' height='25'/>
                    Войти с Google
                </button>

                <button
                    className="flex items-center justify-around bg-gray-800 hover:bg-gray-900 text-white font-medium py-2 px-4 rounded mb-6 w-full">
                    <Image src='/logos/github.svg' alt='' width='25' height='25'/>
                    Войти с GitHub
                </button>

                <div className="text-center mb-6 text-gray-500">или</div>

                <input type="email" placeholder="Почтовый адрес" className="mb-4 p-2 w-full border rounded"/>
                <input type="password" placeholder="Пароль" className="mb-4 p-2 w-full border rounded"/>

                <div className="text-right text-sm mb-6 text-blue-500 hover:text-blue-600 cursor-pointer">
                    <Link href='/restore'>Не помните свой пароль?</Link>
                </div>

                <button className="bg-gray-800 hover:bg-gray-900 text-white font-medium py-2 px-4 rounded w-full">
                    Войти
                </button>
            </div>
        </div>
    );
}